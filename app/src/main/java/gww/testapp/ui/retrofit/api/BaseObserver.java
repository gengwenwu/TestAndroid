package gww.testapp.ui.retrofit.api;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * desc: TODO <br/>
 * time: 2019/3/20 下午4:00 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private Disposable mDisposable;

    /**
     *
     */
    protected abstract void onSuccess(T t);

    /**
     *
     */
    protected abstract void onError();


    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        //e.printStackTrace();
        ToolLog.e(e.getMessage());
        onError();
    }

    @Override
    public void onComplete() {

    }

}
