package gww.testapp.ui.rxjava2.backpressure;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * desc: 背压。<br/>
 * 在RxJava2里，引入了Flowable这个类：Observable不包含BackPressure处理，而Flowable 包含 <br/>
 * time: 2018/5/24 下午7:00 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class TestBackPressure {

    /**
     * 测试Observable背压 <br/>
     * 2.x 版本中 Observable 不再支持背压，发射器生成的数据全部缓存在内存中。<br/>
     * 1, 不支持 backpressure 处理，不会发生 MissingBackpressureException 异常。<br/>
     * 2, 所有没有处理的数据都缓存在内存中，等待被订阅者处理。<br/>
     * 3, 坏处是：当产生的数据过快，内存中缓存的数据越来越多，占用大量内存。<br/>
     */
    public static void testObservable() {
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ToolLog.t("TestBackPressure -> testObservable() accept1:" + aLong + " ============");
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Thread.sleep(1000);
                        ToolLog.t("TestBackPressure -> testObservable() accept2:" + aLong);
                    }
                });
    }

    /**
     * 测试Flowable背压。
     */
    public static void testFlowable() {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Thread.sleep(2000);
                        ToolLog.t("TestBackPressure -> testFlowable() accept2:" + aLong);
                    }
                });
    }

    /**
     * onBackpressureDrop() 一定要放在 interval 后面否则不会生效
     */
    public static void TestOnBackpressureLatest() {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop() //onBackpressureDrop 一定要放在 interval 后面否则不会生效
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Thread.sleep(1000);
                        ToolLog.t("TestBackPressure -> testFlowable() accept2:" + aLong);
                    }
                });
    }

}
