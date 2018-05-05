package gww.testapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * desc: Activity生命周期回调监听 <br/>
 * time: 2018/3/26 下午4:56 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        //ToolLog.v("CallbacksImpl -> onCreated():" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        //ToolLog.v("CallbacksImpl -> onStarted():" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        //ToolLog.v("CallbacksImpl -> onResumed():" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        //ToolLog.v("CallbacksImpl -> onPaused():" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        //ToolLog.v("CallbacksImpl -> onStopped():" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        //ToolLog.v("CallbacksImpl -> onSaveInstanceState():" + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        //ToolLog.v("CallbacksImpl -> onDestroyed():" + activity.getClass().getSimpleName());
    }

}
