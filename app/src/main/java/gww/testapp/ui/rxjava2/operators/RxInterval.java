package gww.testapp.ui.rxjava2.operators;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * desc: 延迟一段时候后，间隔执行操作，默认在新线程，它按固定的时间间隔发射一个无限递增的整数序列。<br/>
 * time: 2018/5/9 下午2:55 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxInterval implements IOperator {

    private static int count = 0;
    private Disposable disposable;

    @Override
    public void test() {
        disposable = Flowable.interval(5, 1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ToolLog.t("RxInterval -> accept()1 aLong:" + aLong + ", count:" + count);
                        count++;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ToolLog.t("RxInterval -> accept()2 aLong:" + aLong + ", count:" + count);

                        if (count == 5) {
                            disposable.dispose();//停止
                            ToolLog.v("RxInterval -> accept()2 dispose!!!");
                        }
                    }
                });
    }

}
