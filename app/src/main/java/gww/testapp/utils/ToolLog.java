package gww.testapp.utils;

import android.util.Log;

/**
 * desc: TODO <br/>
 * time: 2017/8/14 下午2:29 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */
public class ToolLog {

    public static void v(String msg) {
        Log.v("MyConsole", msg);
    }

    public static void e(String msg) {
        Log.e("MyConsole", msg);
    }


    public static void t(String msg) {
        Log.v("MyConsole", "tid:" + Thread.currentThread().getId() + ", msg:" + msg);
    }

}
