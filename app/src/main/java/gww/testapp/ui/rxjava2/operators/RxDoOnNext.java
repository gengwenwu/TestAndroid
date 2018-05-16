package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * doOnNext: 应该不算一个操作符，但考虑到其常用性，还是将它放在了这里。<br/>
 * 它的作用是让订阅者在接收到数据之前干点有意思的事情。<br/>
 * 假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。 <br/>
 * time: 2018/5/16 上午11:32 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxDoOnNext implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        // do something
                        ToolLog.v("RxDoOnNext -> doOnNext:" + integer);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxDoOnNext -> subscribe:" + integer + " =======");
                    }
                });
    }

}
