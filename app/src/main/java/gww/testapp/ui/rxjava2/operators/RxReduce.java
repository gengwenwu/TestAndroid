package gww.testapp.ui.rxjava2.operators;


import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Reduce: 对原始Observable发射数据的第一项应用一个函数，然后再将这个函数的返回值与第二项数据一起传递给函数，<br/>
 * 以此类推，持续这个过程直至原始Observable发射它的最后一项数据并终止，此时Reduce返回的Observable发射这个函数返回的最终值。<br/>
 * time: 2018/5/17 下午3:51 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */

public class RxReduce implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3, 4)
                .reduce(5, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer acc, Integer item) {
                        ToolLog.v("RxReduce -> reduce() acc:" + acc + ", item:" + item);
                        return acc + item;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer sum) throws Exception {
                ToolLog.v("RxReduce -> accept() sum:" + sum);
            }
        });
    }

}
