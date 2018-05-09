package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * zip: 合并事件专用 <br/>
 * 操作符返回一个Observable，它使用这个函数按顺序结合两个或多个Observables发射的数据项，然后它发射这个函数返回的结果。
 * 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据（必须两两配对，多余的舍弃) <br/>
 * 废话这么多其实就一句话：将多个Observable 的数据结合为一个数据源再发射出去。
 * time: 2018/5/9 下午1:44 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxZip implements IOperator {

    @Override
    public void test() {
        Observable.zip(getStringObservable(), getIntObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                ToolLog.v("RxZip -> apply():" + (s + integer));
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ToolLog.v("RxZip -> accept() s:" + s);
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    ToolLog.v("RxZip -> onNext('A')");
                    emitter.onNext("A");
                    ToolLog.v("RxZip -> onNext('B')");
                    emitter.onNext("B");
                    ToolLog.v("RxZip -> onNext('C')");
                    emitter.onNext("C");
                }
            }
        });
    }

    private Observable<Integer> getIntObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    ToolLog.v("RxZip -> onNext(1)");
                    emitter.onNext(1);
                    ToolLog.v("RxZip -> onNext(2)");
                    emitter.onNext(2);
                    ToolLog.v("RxZip -> onNext(3)");
                    emitter.onNext(3);
                    ToolLog.v("RxZip -> onNext(4)");
                    emitter.onNext(4);
                    ToolLog.v("RxZip -> onNext(5)");
                    emitter.onNext(5);
                }
            }
        });
    }

}
