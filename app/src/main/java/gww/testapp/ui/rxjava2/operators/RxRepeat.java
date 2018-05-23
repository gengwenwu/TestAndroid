package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * repeat: 重复的发射数据 <br/>
 * time: 2018/5/23 下午3:26 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxRepeat implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2)
                .repeat(3) //重复3次，无参无限重复
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxRepeat -> accept() integer:" + integer);
                    }
                });
    }

}

