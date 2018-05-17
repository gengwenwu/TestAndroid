package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Merge: 把多个 Observable 结合起来，接受可变参数，也支持迭代器集合 <br/>
 * 它和concat区别在于，不用等到 发射器A 发送完所有的事件再进行 发射器B的发送。<br/>
 * time: 2018/5/17 下午3:32 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */

public class RxMerge implements IOperator {

    @Override
    public void test() {
        Observable.merge(Observable.just("A1", "A2", "A3", "A4")
                , Observable.just("B1", "B2", "B3", "B4", "B5"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToolLog.v("RxMerge -> accept() s:" + s);
                    }
                });
    }

}
