package gww.testapp.ui.rxjava2.operators.base;

import gww.testapp.ui.rxjava2.operators.IOperator;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Observable: 能够发射0或n个数据，并以成功或错误事件终止，Rx2.0之后不支持背压。<br/>
 * time: 2018/5/30 下午2:09 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxObservable implements IOperator {

    @Override
    public void test() {
        // TODO
        // Observable.combineLatest XX()
        // Observable.concat XX()
        // Observable.from XX()
        // Observable.create()
        // Observable.defer()
        // Observable.empty()
        // Observable.generate()
        // Observable.error()
        // Observable.interval()
        // Observable.just()
        // Observable.merge()
        // Observable.never()
        // Observable.range()
        // Observable.sequenceEqual()
        // Observable.switchOnNext()
        // Observable.timer()
        // Observable.using()
        // Observable.wrap()
        // Observable.zip()
        // Observable.unsafeCreate()


        Observable.fromArray(1, 2, 3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.t("RxObservable -> accept():" + integer);
                    }
                });

        ToolLog.t("========= empty() begin.");
        Observable.empty()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ToolLog.t("RxObservable -> onSubscribe()");
                    }

                    @Override
                    public void onNext(Object o) {
                        ToolLog.t("RxObservable -> onNext():" + o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToolLog.t("RxObservable -> onError():" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        ToolLog.t("RxObservable -> onComplete()");
                    }
                });


        ToolLog.t("========= never() begin.");
        Observable.never()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ToolLog.t("RxObservable -> onSubscribe()");
                    }

                    @Override
                    public void onNext(Object o) {
                        ToolLog.t("RxObservable -> onNext():" + o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToolLog.t("RxObservable -> onError():" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        ToolLog.t("RxObservable -> onComplete()");
                    }
                });
    }

}