package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * scan:
 * 解释1：连续地对数据序列的每一项应用一个函数，然后连续发射结果 <br/>
 * 解释2：作用和reduce一致，唯一区别是reduce是个只追求结果的坏人，而scan会始终如一地把每一个步骤都输出。 <br/>
 * time: 2018/5/17 下午4:00 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */

public class RxScan implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3)
                .scan(5, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer acc, Integer item) throws Exception {
                        ToolLog.v("RxScan -> scan() acc:" + acc + ", item:" + item);
                        return acc + item;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer sum) throws Exception {
                ToolLog.v("RxScan -> accept() sum:" + sum);
            }
        });
    }

}
