package gww.testapp.net.retrofit;

import gww.testapp.net.okhttp3.OkHttpManager;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * desc: TODO <br/>
 * time: 2019/3/20 上午11:34 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class RetrofitClient {

    private static Retrofit mRetrofit;

    public void init() {
        Retrofit.Builder builder = new Retrofit.Builder();
        mRetrofit = doConfig(builder).build();
    }

    public Retrofit.Builder doConfig(Retrofit.Builder builder) {
        // TODO: 2019/3/20 Logan
        builder.baseUrl("http://weeklyapp.jollytest.com/")
                .client(OkHttpManager.getInstance().getClient())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder;
    }

    public static RetrofitClient getInstance() {
        if (mRetrofit == null || mRetrofit.baseUrl() == null) {
            RetrofitClientHolder.INSTANCE.init();
        }

        return RetrofitClientHolder.INSTANCE;
    }


    public <T> T createService(final Class<T> service) {
        return mRetrofit.create(service);
    }

    static class RetrofitClientHolder {
        static final RetrofitClient INSTANCE = new RetrofitClient();
    }

}
