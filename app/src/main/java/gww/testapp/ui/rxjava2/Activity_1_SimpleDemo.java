package gww.testapp.ui.rxjava2;

import android.app.Activity;
import android.os.Bundle;

import gww.testapp.R;
import gww.testapp.ui.rxjava2.backpressure.TestBackPressure;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

        // new RxCreate().test();
        // testScheduler();

        /* 操作符 */
        // new RxMap().test();
        // new RxConcat().test();
        // new RxFlatMap().test();
        // new RxZip().test();
        // new RxDistinct().test();
        // new RxFilter().test();
        // new RxBuffer().test();
        // new RxTimer().test();
        // new RxInterval().test();
        // new RxDoOnNext().test();
        // new RxSkip().test();
        // new RxTake().test();

        // new RxSingle().test();
        // new RxDebounce().test();
        // new RxDefer().test();
        // new RxLast().test();
        // new RxMerge().test();
        // new RxReduce().test();
        // new RxScan().test();

        // new RxWindow().test();

        // new RxRepeat().test();
        // new RxRange().test();
        // new RxFromArray().test();
        // new RxFromIterable().test();
        // new RxToList().test();
        // new RxDelay().test();

        // TestBackPressure.testObservable();
        TestBackPressure.testFlowable();

    }

    /**
     * 线程
     */
    private void testScheduler() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                ToolLog.t("subscribe()");
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()) // subscribeOn() 以第一个为准，剩下的无效
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // observeOn() 仅影响下面的语句
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.t("doOnNext() -> accept() 1");
                    }
                }).observeOn(Schedulers.io()) // observeOn() 仅影响下面的语句
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.t("doOnNext()() -> accept() 2");
                    }
                });
    }


}
