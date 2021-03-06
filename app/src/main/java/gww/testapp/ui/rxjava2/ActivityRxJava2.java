package gww.testapp.ui.rxjava2;

import android.app.Activity;
import android.os.Bundle;

import gww.testapp.R;
import gww.testapp.ui.rxjava2.operators.RxCompose;

/**
 * desc: RxJava2简单例子 <br/>
 * time: 2018/5/5 上午8:41 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class ActivityRxJava2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_simple_demo);

        /*========== Rx2.x 基础类 ==========*/
        /* Observable 例子 */
        // new RxCreate().test();
        // new RxObservable().test();

        /* Single 例子 */
        // new RxSingle().test();

        /* Flowable 例子 */
        //RxFlowable.testFlowable();
        //RxFlowable.testObservable(); //背压
        //RxFlowable.TestOnBackpressureLatest(); //背压

        /* Completabl 例子 */
        // RxCompletable.testCompletable();
        // RxCompletable.testAndThen();

        /*  Maybe例子 */
        // RxMaybe.testMaybe();
        // RxMaybe.testMaybe2();

        /*========== 线程切换 ==========*/
        // new RxScheduler().test();

        /*========== 操作符 ==========*/
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

        // new RxThrottleFirst().test();
        // new RxElement().test();
        new RxCompose().test();


    }


}
