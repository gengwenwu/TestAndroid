package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc: 只发射最后一项（或者满足某个条件的最后一项）数据 <br/>
 * time: 2018/5/17 下午3:03 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxLast implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer > 3;
//                    }
//                })
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxLast -> accept() i:" + integer);
                    }
                });
    }

}
