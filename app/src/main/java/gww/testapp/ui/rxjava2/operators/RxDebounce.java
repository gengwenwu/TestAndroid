package gww.testapp.ui.rxjava2.operators;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * debounce: 对源Observable间隔期产生的结果进行过滤，如果在这个规定的间隔期内没有别的结果产生，则将这个结果提交给订阅者，<br/>
 * 否则忽略该结果，原理有点像光学防抖。<br/>
 * 注意与ThrottleFirst区别。
 * time: 2018/5/17 下午1:47 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxDebounce implements IOperator {

    @Override
    public void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    ToolLog.t("RxDebounce -> onNext(" + i + ")");
                    emitter.onNext(i);
                    Thread.sleep(i * 1000);
                }

                emitter.onComplete();
            }
        }).debounce(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.t("RxDebounce -> value:" + integer);
                    }
                });
    }

}