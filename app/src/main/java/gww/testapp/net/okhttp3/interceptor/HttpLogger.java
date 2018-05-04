package gww.testapp.net.okhttp3.interceptor;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * desc: TODO <br/>
 * time: 2017/8/14 下午4:44 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {

	@Override
	public void log(String message) {
		Log.d("OkHttp3", message);
	}

}
