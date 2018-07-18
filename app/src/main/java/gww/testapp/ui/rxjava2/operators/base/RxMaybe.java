package gww.testapp.ui.rxjava2.operators.base;

import gww.testapp.utils.ToolLog;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Maybe：能够发射0或者1个数据，要么成功，要么失败。有点类似于Optional。<br/>
 * Maybe 转换成Observable、Flowable、Single，只需相应地调用toObservable()、toFlowable()、toSingle()。
 * time: 2018/7/18 下午2:35 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxMaybe {

    public static void testMaybe() {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                // emitter.onComplete(); //先调用了onComplete()，再调用了onSuccess()，下游也不会发射任何数据。
                emitter.onSuccess("T1");
                emitter.onSuccess("T2"); //下游不会处理T2
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ToolLog.t("RxMaybe -> testMaybe():" + s);
            }
        });
    }


    public static void testMaybe2() {
        // Maybe在没有数据发射时候，subscribe会调用MaybeObserver的onComplete()。
        // 如果Maybe有数据发射或者调用了onError()，是不会再执行MaybeObserver的onComplete()。

        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                // emitter.onComplete();
                emitter.onSuccess("S1");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ToolLog.t("RxMaybe -> testMaybe2() Success:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToolLog.t("RxMaybe -> testMaybe2() Error:" + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                ToolLog.t("RxMaybe -> testMaybe2() Complete: Maybe 没有数据发送，会调用该句。");
            }
        });
    }

}
