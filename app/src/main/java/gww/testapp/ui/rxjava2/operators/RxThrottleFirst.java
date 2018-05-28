package gww.testapp.ui.rxjava2.operators;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * throttleFirst: 允许设置一个时间长度，之后它会发送固定时间长度内的第一个事件，而屏蔽其它事件，在间隔达到设置的时间后，可以再发送下一个事件。 <br/>
 * 注意与Debounce区别。TODO
 * time: 2018/5/28 下午3:44 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxThrottleFirst implements IOperator {

    @Override
    public void test() {
        Observable.interval(1, 200, TimeUnit.MILLISECONDS)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ToolLog.v("RxThrottleFirst -> accept() aLong:" + aLong);
                    }
                });
    }

}