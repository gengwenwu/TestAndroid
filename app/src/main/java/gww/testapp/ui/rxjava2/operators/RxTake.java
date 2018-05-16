package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Take：可以修改Observable的行为，只发射前面N项数据，忽略剩余的数据。 <br/>
 * TakeLast：可以修改原始Observable的行为，只发射Observable发射的后N项数据，忽略前面的数据。
 * time: 2018/5/16 下午1:12 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxTake implements IOperator {

    @Override
    public void test() {
        Flowable.fromArray(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxTake -> take() :" + integer);
                    }
                });

        Flowable.fromArray("H", "e", "l", "l", "o")
                .takeLast(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToolLog.v("RxTake -> takeLast() :" + s);
                    }
                });
    }

}
