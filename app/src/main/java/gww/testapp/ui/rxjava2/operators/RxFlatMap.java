package gww.testapp.ui.rxjava2.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gww.testapp.data.SimpleData;
import gww.testapp.ui.rxjava2.model.Car;
import gww.testapp.ui.rxjava2.model.User;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * FlatMap：使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，<br/>
 * 这个函数返回一个本身也发射数据的Observable，然后FlatMap合并这些Observables发射的数据，<br/>
 * 最后将合并后的结果当做它自己的数据序列发射。<br/>
 * 注意：FlatMap对这些Observables发射的数据做的是合并(merge)操作，因此它们可能是交错的。但ConcatMap就是有序的。<br/>
 * time: 2018/5/5 下午6:42 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxFlatMap implements IOperator {

    @Override
    public void test() {
        Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                ToolLog.v("RxFlatMap -> subscribe() 张三");
                emitter.onNext(SimpleData.USER_ZHANG_SAN);
                ToolLog.v("RxFlatMap -> subscribe() 李四");
                emitter.onNext(SimpleData.USER_LI_SI);
            }
        }).flatMap(new Function<User, ObservableSource<Car>>() {
            @Override
            public ObservableSource<Car> apply(User user) throws Exception {
                ToolLog.v("RxFlatMap -> apply() user name:" + user.getName());
                return Observable.fromIterable(user.getCars());
            }
        }).subscribe(new Consumer<Car>() {
            @Override
            public void accept(Car car) throws Exception {
                ToolLog.v("RxFlatMap -> accept() car name:" + car.getName());
            }
        });

        ToolLog.e("==============");

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                ToolLog.t("RxFlatMap -> subscribe() 1");
                emitter.onNext(1);
                ToolLog.t("RxFlatMap -> subscribe() 2");
                emitter.onNext(2);
                ToolLog.t("RxFlatMap -> subscribe() 3");
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }

                int delayTime = (int) (1 + Math.random() * 10);
                ToolLog.t("RxFlatMap -> apply() delayTime:" + delayTime);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MICROSECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String str) throws Exception {
                        ToolLog.t("RxFlatMap -> accept():" + str);
                    }
                });

    }
}