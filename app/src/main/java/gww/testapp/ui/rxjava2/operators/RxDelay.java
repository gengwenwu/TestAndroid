package gww.testapp.ui.rxjava2.operators;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * delay: 延迟发射数据 <br/>
 * time: 2018/5/23 下午5:12 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxDelay implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3)
                .delay(3, TimeUnit.SECONDS) //延迟3秒钟，然后在发射数据
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxDelay -> delay():" + integer);
                    }
                });
    }
}
