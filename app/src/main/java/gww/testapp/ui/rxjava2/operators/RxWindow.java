package gww.testapp.ui.rxjava2.operators;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Window: 定期或指定个数的将来自原始Observable数据封装为一个Observable窗口，发射这些窗口，而不是每次发射一项数据 <br/>
 * Window和Buffer类似，但不是发射来自原始Observable的数据包，它发射的是Observables，<br/>
 * 这些Observables中的每一个都发射原始Observable数据的一个子集，最后发射一个onCompleted通知。<br/>
 * 和Buffer一样，Window有很多变体，每一种都以自己的方式将原始Observable分解为多个作为结果的Observable，每一个都包含一个映射原始数据的window。<br/>
 * time: 2018/5/17 下午4:44 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxWindow implements IOperator {

    @Override
    public void test() {
        /* 指定个数发送数据 */
//        Observable.range(1, 10)
//                .window(3) // 接收3个数据作为一个窗口，即：Observable<Integer>
//                .subscribe(new Consumer<Observable<Integer>>() {
//                    @Override
//                    public void accept(Observable<Integer> observable) throws Exception {
//                        observable.subscribe(new Consumer<Integer>() {
//                            @Override
//                            public void accept(Integer integer) throws Exception {
//                                ToolLog.t("RxWindow -》 accept() : " + integer + " of window " + observable);
//                            }
//                        });
//                    }
//                });

        /* 定时发送数据 */
        Observable.interval(1, TimeUnit.SECONDS) //间隔1秒发一次
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ToolLog.t("RxWindow doOnNext():" + aLong);
                    }
                })
                .take(10) // 最多接收10个
                .window(5, TimeUnit.SECONDS) // 接收3秒数据作为一个窗口，即：Observable<Long>
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        ToolLog.t("RxWindow ------> accept() : " + aLong + " of window " + longObservable);
                                    }
                                });
                    }
                });

    }


}
