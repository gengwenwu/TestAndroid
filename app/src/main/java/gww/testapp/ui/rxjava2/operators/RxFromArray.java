package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc: 遍历数组 <br/>
 * time: 2018/5/23 下午3:44 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxFromArray implements IOperator {

    @Override
    public void test() {
        Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable.fromArray(items)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("FromArray -> integer:" + integer);
                    }
                });
    }

}
