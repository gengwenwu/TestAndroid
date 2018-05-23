package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Range: 发射特定整数序列的Observable <br/>
 * time: 2018/5/23 下午3:34 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxRange implements IOperator {

    @Override
    public void test() {
        Observable.range(1, 5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxRange -> integer:" + integer);
                    }
                });
    }

}
