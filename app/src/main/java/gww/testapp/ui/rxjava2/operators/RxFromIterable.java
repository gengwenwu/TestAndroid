package gww.testapp.ui.rxjava2.operators;

import java.util.Arrays;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc: 遍历集合 <br/>
 * time: 2018/5/23 下午3:51 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxFromIterable implements IOperator {

    @Override
    public void test() {

        Observable.fromIterable(Arrays.asList("a", "b", "c"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToolLog.v("RxFromIterable -> s:" + s);
                    }
                });
    }

}
