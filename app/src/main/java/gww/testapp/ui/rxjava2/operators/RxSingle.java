package gww.testapp.ui.rxjava2.operators;

import java.util.Random;

import gww.testapp.utils.ToolLog;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Single: 类似于Observable，不同的是，它总是只发射一个值，或者一个错误通知，而不是发射一系列的值。 <br/>
 * 因此，不同于Observable需要三个方法onNext, onError, onCompleted，订阅Single只需要两个方法onError()或者onSuccess()<br/>
 * time: 2018/5/16 下午1:39 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxSingle implements IOperator {

    @Override
    public void test() {
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        ToolLog.t("RxSingle -> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        ToolLog.t("RxSingle -> onSuccess():" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToolLog.t("RxSingle -> onError():" + e.getMessage());
                    }
                });
    }

}
