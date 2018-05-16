package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Skip: 代表跳过 count 个数目开始接收数据  <br/>
 * time: 2018/5/16 上午11:41 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxSkip implements IOperator {

    @Override
    public void test() {
        Observable.just(1, 2, 3, 4)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxSkip -> skip(count) :" + integer);
                    }
                });

//        ToolLog.v("===================");
//        Observable.just(5, 6, 7, 8)
//                .skip(1, TimeUnit.SECONDS)
//                .observeOn(Schedulers.io())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        TimeUnit.SECONDS.sleep(2);
//                        ToolLog.t("RxSkip -> skip(time) :" + integer);
//                    }
//                });

    }

}
