package gww.testapp;

import android.app.Application;

/**
 * desc: TODO <br/>
 * time: 2018/1/31 下午1:43 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */

public class ApplicationMain extends Application {

    public static ApplicationMain mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImpl());
    }

}
