package gww.testapp.ui.rxjava2.operators;

import java.util.concurrent.Callable;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * defer操作符 <br/>
 * 解释1：直到有订阅(即：调用subscribe())，才会创建Observable，具有延时的效果。<br/>
 * 解释2：每次订阅都会创建一个新的Observable，并且如果该Observable没有被订阅，就不会生成新的Observable。<br/>
 * 与just区别是，just是立即创建Observable对象，而defer只有subscribe()的时候才创建。<br/>
 * time: 2018/5/17 下午2:30 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxDefer implements IOperator {

    @Override
    public void test() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                ToolLog.v("RxDefer -> ObservableSource()");
                return Observable.just(1, 2, 3);
            }
        });

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                ToolLog.v("RxDefer -> onSubscribe():" + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                ToolLog.v("RxDefer -> onNext():" + integer);
            }

            @Override
            public void onError(Throwable e) {
                ToolLog.v("RxDefer -> onError():" + e.getMessage());
            }

            @Override
            public void onComplete() {
                ToolLog.v("RxDefer -> onComplete()");
            }
        });

    }


}
