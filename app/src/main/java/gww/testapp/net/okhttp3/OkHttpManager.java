package gww.testapp.net.okhttp3;

import java.util.concurrent.TimeUnit;

import gww.testapp.net.okhttp3.interceptor.HttpLogger;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * desc: OkHttp <br/>
 * time: 2017/8/14 下午5:50 <br/>
 * author: 居廉 <br/>
 * since V 1.0 <br/>
 */
public class OkHttpManager {

    private OkHttpClient mClient;


    private OkHttpManager() {
        init();
    }

    public static OkHttpManager getInstance() {
        return RetrofitClientHolder.INSTANCE;
    }

    private void init() {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();

        mClient = builder
                //.addInterceptor(getLogInterceptor()) //Log
                .retryOnConnectionFailure(false) //取消失败重发
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        // 缓存
        // int cacheSize = 10 * 1024 * 1024; // 10 MiB
        // Cache cache = new Cache(cacheDirectory, cacheSize);
        // OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // builder.cache(cache);
    }

    public OkHttpClient getClient() {
        return mClient;
    }

    public static Interceptor getLogInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        //TODO 根据配置
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    static class RetrofitClientHolder {
        static final OkHttpManager INSTANCE = new OkHttpManager();
    }

}
