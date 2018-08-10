package gww.testapp.ui.rxjava2.operators.base;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Completable: 从来不发射数据，只处理 onComplete 和 onError 事件。侧重于观察结果。可以看成是Rx的Runnable。<br/>
 * 同时 Completable 并没有map、flatMap等操作符，它的操作符比起 Observable/Flowable 要少得多。<br/>
 * time: 2018/7/18 下午1:42 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxCompletable {

    public static void testCompletable() {
        Completable.fromAction(() -> {
            // 后台线程执行
            ToolLog.t("RxCompletable -> testCompletable()");
        }).subscribe();
    }

    /**
     * Completable 经常会结合andThen操作符 <br/>
     * andThen有多个重载的方法，正好对应了五种被观察者的类型 <br/>
     */
    public static void testAndThen() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                try {
                    ToolLog.t("RxCompletable -> testAndThen() 1");
                    TimeUnit.SECONDS.sleep(1);
                    emitter.onComplete();
                    ToolLog.t("RxCompletable -> testAndThen() 2");
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        })
                //.toObservable() 可以通过toXX方法转换成Observable、Flowable、Single以及Maybe。
                .andThen(Observable.range(1, 10))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.t("RxCompletable -> testAndThen() accept:" + integer);
                    }

                });
    }

}
