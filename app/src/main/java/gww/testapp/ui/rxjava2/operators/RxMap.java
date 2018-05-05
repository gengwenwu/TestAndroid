package gww.testapp.ui.rxjava2.operators;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * desc: 对上游发送数据进行对象转换。<br/>
 * 譬如：采用 map 操作符进行网络数据解析。
 * time: 2018/5/5 下午12:01 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxMap implements IOperator {

    @Override
    public void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                ToolLog.t("RxMap -> apply():" + integer);
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ToolLog.t("RxMap -> accept():" + s);
            }
        });

        ToolLog.e("========================");

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                ToolLog.t("RxMap -> subscribe()");
                emitter.onNext("{\"age\":32,\"name\":\"张三\"}");
            }
        }).map(new Function<String, User>() {
            @Override
            public User apply(@NonNull String response) throws Exception {
                ToolLog.t("RxMap -> map() -> apply()");
                return JSON.parseObject(response, User.class);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        ToolLog.t("RxMap -> doOnNext() -> apply()");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        ToolLog.t("RxMap -> subscribe() -> apply():" + user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToolLog.t("RxMap -> subscribe() -> apply(Throwable):" + throwable.getMessage());
                    }
                });

    }


}
