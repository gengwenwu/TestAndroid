package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Filter: 过滤操作符，取返回true值  <br/>
 * time: 2018/5/9 下午7:01 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxFilter implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 20, -5, 19)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer >= 10;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                ToolLog.v("RxFilter -> accept():" + integer);
            }
        });

    }

}
