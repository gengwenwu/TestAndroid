package gww.testapp.ui.rxjava2.scheduler;

import android.os.Looper;
import android.util.Log;

import gww.testapp.ui.rxjava2.operators.IOperator;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * desc: Rx线程=  <br/>
 * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
 * 以第一个为准，剩下的无效。
 * observeOn()，指定Subscriber、Consumer、Action等的回调发生的IO线程，也可以叫做事件消费的线程。。
 * time: 2018/5/28 下午2:37 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxScheduler implements IOperator {

    private final String TAG = "RxScheduler";


    @Override
    public void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                ToolLog.t("subscribe(): isMainThread:" + (Thread.currentThread() == Looper.getMainLooper().getThread()));
                emitter.onNext(1);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.newThread()) // subscribeOn() 以第一个为准，剩下的无效
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

    /**
     * 线程切换 林佐跃
     */
    public void test2() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            threadInfo("ObservableOnSubscribe");
            emitter.onNext(1);
            threadInfo("ObservableOnSubscribe");
            emitter.onNext(2);
            threadInfo("ObservableOnSubscribe");
            emitter.onComplete();
        }).subscribeOn(Schedulers.newThread()
        ).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                threadInfo("filter");
                return integer % 2 == 0;
            }
        }).subscribeOn(Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()
        ).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                threadInfo("Observer");
                Log.e(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void threadInfo(String observer) {
        Log.e(TAG, observer + " threadInfo ->" + Thread.currentThread().getId());
    }

}
