package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
    }

}
