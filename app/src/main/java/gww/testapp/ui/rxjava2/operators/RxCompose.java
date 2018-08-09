package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Compose: 使用compose 消除重复代码，而不要打断链式结构，它与Transformer结合一起使用。 <br/>
 * 它影响到是整个流。
 * time: 2018/8/9 下午2:09 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxCompose implements IOperator {

    // 参考地址：
    // https://blog.csdn.net/u013378580/article/details/51607677
    // https://blog.csdn.net/love_yan_1314/article/details/60465171
    // https://www.jianshu.com/p/3d0bd54834b0
    //

    @Override
    public void test() {
        // 需求
//        Observable.create((ObservableOnSubscribe<String[]>) emitter -> {
//            emitter.onNext(getServerData());
//        }).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(array -> showServerData(array));


        // 反模式
//        applySchedulers(
//                Observable.create((ObservableOnSubscribe<String[]>) emitter -> {
//                    emitter.onNext(getServerData());
//                })
//        ).subscribe(array -> showServerData(array));


        // 使用 compose + ObservableTransformer
        Observable.create((ObservableOnSubscribe<String[]>) emitter -> {
            emitter.onNext(getServerData());
        }).compose(applySchedulersForCompose())
                .subscribe(array -> showServerData(array));
    }


    // 反模式写法
    private <T> Observable<T> applySchedulers(Observable<T> tObservable) {
        return tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private <T> ObservableTransformer<T, T> applySchedulersForCompose() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    private String[] getServerData() {
        ToolLog.t("RxCompose -> getServerData()");

        return new String[]{
                "Hello", "World", "!"
        };
    }

    private void showServerData(String[] array) {
        StringBuffer sb = new StringBuffer();
        for (String str : array) {
            sb.append(str + " ");
        }
        ToolLog.t("RxCompose -> showServerData(): " + sb.toString());
    }

}
