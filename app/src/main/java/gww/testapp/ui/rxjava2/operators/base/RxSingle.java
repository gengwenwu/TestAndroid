package gww.testapp.ui.rxjava2.operators.base;

import gww.testapp.ui.rxjava2.operators.IOperator;
import gww.testapp.utils.ToolLog;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * Single: 类似于Observable，是Observable一种变种。Rx2.X 引入。<br/>
 * 不同的是，它总是只发射一个值或者一个错误通知，而不是发射一系列的值。 <br/>
 * 因此，不同于Observable需要三个方法onNext, onError, onCompleted，订阅Single只需要两个方法：<br/>
 * 1, onSuccess(): 发射单个的值到这个方法。<br/>
 * 2, onError(): 如果无法发射需要的值，Single发射一个Throwable对象到这个方法。<br/>
 * Single只会调用这两个方法中的一个，而且只会调用一次，调用了任何一个方法之后，订阅关系终止。<br/>
 * time: 2018/5/16 下午1:39 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxSingle implements IOperator {

    @Override
    public void test() {
        Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                final int maxNum = 2;
                final int num = getRandomIntVal(maxNum);
                ToolLog.t("RxSingle -> num:" + num);

                if (num < 2) {
                    throw new Exception("RxSingle -> num小于" + 2);
                } else {
                    emitter.onSuccess(num);
                }
            }
        }).subscribe(new SingleObserver<Integer>() {
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

    /**
     * @param rangeNum 最大随机数
     * @return 随机数
     * 返回1至参数rangeNum之间的随机数, 包含1和rangeNum
     */
    private int getRandomIntVal(int rangeNum) {
        long randomValue = (long) (Math.random() * rangeNum + 1);

        if (randomValue > Integer.MAX_VALUE) {
            randomValue = Integer.MAX_VALUE;
        }

        return (int) randomValue;
    }

}
