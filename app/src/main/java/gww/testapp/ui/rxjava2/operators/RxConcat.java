package gww.testapp.ui.rxjava2.operators;

import com.alibaba.fastjson.JSON;

import java.util.Random;

import gww.testapp.data.SimpleData;
import gww.testapp.ui.rxjava2.model.User;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Concat：不交错的发射两个或多个Observable的发射，
 * 只有前一个 Observable 终止(onComplete)后才会订阅下一个 Observable。<br/>
 * time: 2018/5/5 下午13:17 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxConcat implements IOperator {

    @Override
    public void test() {
        Observable.concat(Observable.just(1, 2)
                , Observable.just(3, 4)
                , Observable.just(5, 6))

                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToolLog.v("RxConcat -> accept():" + integer);
                    }
                });

        ToolLog.e("===============");

        Observable<String> cache = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String cacheData = getCacheData();

                if (cacheData != null) {
                    ToolLog.v("RxConcat -> subscribe(), cacheData:" + cacheData);
                    emitter.onNext(cacheData); // 开始下一个Observable
                } else {
                    ToolLog.v("RxConcat -> subscribe(), no cache data!");
                    emitter.onComplete(); // 终止下一个Observable
                }
            }
        });
        Observable<String> network = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                ToolLog.v("RxConcat -> subscribe(), 取network数据");
                String netData = SimpleData.USER_LI_SI_JSON;
                emitter.onNext(netData);
            }
        });

        Observable.concat(cache, network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, User>() {
                    @Override
                    public User apply(String userStr) throws Exception {
                        return JSON.parseObject(userStr, User.class);
                    }
                })
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        ToolLog.v("RxConcat -> accept() user.name:" + user.getName());
                    }
                });

    }


    private String getCacheData() {
        int value = new Random().nextInt(2);
        ToolLog.v("getCacheData() -> value:" + value);

        if (value > 0) {
            return SimpleData.USER_ZHANG_SAN_JSON;
        } else {
            return null;
        }
    }


}
