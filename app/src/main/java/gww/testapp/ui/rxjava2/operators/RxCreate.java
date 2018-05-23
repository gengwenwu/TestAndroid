package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * create: 主要用于产生一个Observable被观察者对象，<br/>
 * 后期统一把被观察者Observable称为发射器（上游事件），观察者Observer称为接收器（下游事件）。<br/>
 * time: 2018/5/23 下午2:36 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxCreate implements IOperator {

    @Override
    public void test() {
        doOpCreateSimple();
        ToolLog.v("===================");
        doConsumerSimple();
    }

    private void doOpCreateSimple() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception { // 第一步：初始化Observable
                ToolLog.v("RxCreate() -> Observable Emitter 1");
                emitter.onNext(1);
                ToolLog.v("RxCreate() -> Observable Emitter 2");
                emitter.onNext(2);
                ToolLog.v("RxCreate() -> Observable Emitter 3");
                emitter.onNext(3);
                emitter.onComplete();
                ToolLog.v("RxCreate() -> Observable Emitter 4");
                emitter.onNext(4);
                // 输出结果：
                // onSubscribe(Disposable):false
                // Observable Emitter 1
                // onNext(Integer):1
                // Observable Emitter 2
                // onNext(Integer):2
            }
        }).subscribe(new Observer<Integer>() { // 第三步：订阅
            private int i;
            private Disposable mDisposable;

            // 第二步：初始化Observer
            @Override
            public void onSubscribe(Disposable d) {
                ToolLog.v("RxCreate() -> onSubscribe(Disposable):" + d.isDisposed());
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                ToolLog.v("RxCreate() -> onNext(Integer):" + integer);
                i++;

                if (i == 2) {
                    ToolLog.v("RxCreate() -> onNext(Integer):" + integer + ", dispose()。");
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToolLog.v("RxCreate() -> onError(Throwable):" + e.getMessage());
            }

            @Override
            public void onComplete() {
                ToolLog.v("RxCreate() -> onComplete()");
            }
        });
    }

    /**
     * Consumer 代替 Observer
     */
    private void doConsumerSimple() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onComplete();
                emitter.onNext(2);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                ToolLog.v("RxCreate() -> accept():" + integer);
            }
        });
    }

}
