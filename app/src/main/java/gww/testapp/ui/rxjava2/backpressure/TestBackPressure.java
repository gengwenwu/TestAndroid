package gww.testapp.ui.rxjava2.backpressure;

import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 背压产生的原因： 被观察者发送消息太快以至于它的操作符或者订阅者不能及时处理相关的消息。<br/>
 * 在RxJava2里，引入了Flowable这个类，Observable不包含BackPressure处理，而Flowable包含（默认128个）<br/>
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
                        ToolLog.t("TestBackPressure -> testObservable() sleep1:" + aLong);
                        Thread.sleep(1000);
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
                        ToolLog.t("TestBackPressure -> testFlowable() sleep2:" + aLong);
                        Thread.sleep(200);
                    }
                });
    }

    /**
     * 测试 Flowable的onBackpressureDrop() <br/>
     * 当缓冲区数据满 128 个时候，再新来的数据就会被丢弃，如果此时有数据被消费了，那么就会把当前最新产生的数据，放到缓冲区。<br/>
     * 简单来说 Drop 就是直接把存不下的事件丢弃。<br/>
     */
    public static void TestOnBackpressureLatest() {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop() // onBackpressureDrop() 一定要放在 interval后面，否则不会生效
                // .onBackpressureLatest() // 只保留最新的事件。
                // .onBackpressureBuffer() // 默认情况下缓存所有的数据，不会丢弃数据，这个方法可以解决背压问题，但是它有像 Observable 一样的缺点，缓存数据太多，占用太多内存。
                // .onBackpressureBuffer(130) //设置缓存队列大小，但是如果缓冲数据超过了设置的值，就会报错，发生崩溃。
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        ToolLog.t("TestBackPressure -> TestOnBackpressureLatest() sleep3:" + aLong);
                        Thread.sleep(100);
                    }
                });
    }

}
