package gww.testapp.ui.rxjava2.operators;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Timer: 延迟执行一段时间后执行一段逻辑。即：x秒后执行y操作<br/>
 * timer默认在新的线程上执行。
 * time: 2018/5/16 上午11:12 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxTimer implements IOperator {

    @Override
    public void test() {
        Observable.timer(2, TimeUnit.SECONDS)
                //.subscribeOn(AndroidSchedulers.mainThread()) timer默认在新线程上，可以切换回主线程消费
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ToolLog.t("RxTimer -> accept() :" + aLong);
                    }
                });
    }

}
