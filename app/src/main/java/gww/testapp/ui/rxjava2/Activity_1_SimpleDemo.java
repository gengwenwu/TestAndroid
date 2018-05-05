package gww.testapp.ui.rxjava2;

import android.app.Activity;
import android.os.Bundle;

import gww.testapp.R;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc: RxJava2简单例子 <br/>
 * time: 2018/5/5 上午8:41 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class Activity_1_SimpleDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_simple_demo);
        testSimpleDemo();
    }

    private void testSimpleDemo() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        });
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception { // 第一步：初始化Observable
                ToolLog.v("Observable Emitter 1");
                emitter.onNext(1);
                ToolLog.v("Observable Emitter 2");
                emitter.onNext(2);
                ToolLog.v("Observable Emitter 3");
                emitter.onNext(3);
                emitter.onComplete();
                ToolLog.v("Observable Emitter 4");
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
                ToolLog.v("onSubscribe(Disposable):" + d.isDisposed());
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                ToolLog.v("onNext(Integer):" + integer);
                i++;

                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                ToolLog.v("onError(Throwable):" + e.getMessage());
            }

            @Override
            public void onComplete() {
                ToolLog.v("onComplete()");
            }
        });
    }

}
