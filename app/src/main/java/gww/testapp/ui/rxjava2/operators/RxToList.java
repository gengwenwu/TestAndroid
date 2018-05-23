package gww.testapp.ui.rxjava2.operators;

import java.util.List;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * ToList: 把数据转换成 List 集合 <br/>
 * time: 2018/5/23 下午4:00 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxToList implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3, 4)
                .toList()
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        ToolLog.v("RxToList -> accept:" + integers);
                    }
                });

    }

}
