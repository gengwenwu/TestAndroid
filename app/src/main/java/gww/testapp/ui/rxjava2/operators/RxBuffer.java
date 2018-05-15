package gww.testapp.ui.rxjava2.operators;

import java.util.List;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Buffer(count,skip)，作用是将Observable中的数据按skip(步长)分成最大不超过count的buffer，然后生成一个 Observable。
 * time: 2018/5/9 下午7:10 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxBuffer implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        StringBuilder sb = new StringBuilder("size:" + integers.size() + ", items:");


                        for (Integer i : integers) {
                            sb.append(i + ", ");
                        }

                        ToolLog.v("RxBuffer -> accept() :" + sb.toString());
                    }
                });
    }

}
