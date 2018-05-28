package gww.testapp.ui.rxjava2.scheduler;

import gww.testapp.ui.rxjava2.operators.IOperator;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * desc: Rx线程=  <br/>
 * subscribeOn(): 指定subscribe()发生的IO线程，以第一个为准，剩下的无效。
 * observeOn()，指定Subscriber、Consumer的回调发生的IO线程。在该语句的下一个Subscriber、或Consumer产生效果。
 * time: 2018/5/28 下午2:37 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxScheduler implements IOperator {

    @Override
    public void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                ToolLog.t("subscribe()");
                emitter.onNext(1);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()) // subscribeOn() 以第一个为准，剩下的无效
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // observeOn() 仅影响下面的语句
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.t("doOnNext() -> accept() 1");
                    }
                }).observeOn(Schedulers.io()) // observeOn() 仅影响下面的语句
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.t("doOnNext()() -> accept() 2");
                    }
                });
    }

}
