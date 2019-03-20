package gww.testapp.ui.retrofit;

import android.app.Activity;
import android.os.Bundle;

import gww.testapp.R;
import gww.testapp.net.retrofit.RetrofitClient;
import gww.testapp.ui.retrofit.api.DefaultRepoObserver;
import gww.testapp.ui.retrofit.api.IAPI;
import gww.testapp.utils.ToolLog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * desc: Retrofit 测试 <br/>
 * time: 2018/3/26 下午4:42 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class ActivityRetrofit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        findViewById(R.id.btn_b).setOnClickListener(v -> {
            IAPI api = RetrofitClient.getInstance().createService(IAPI.class);
            GoodsDetailParams params = new GoodsDetailParams(14023);
            init(params);

            api.query(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultRepoObserver<String>() {
                        @Override
                        protected void onSuccess(String str) {
                            ToolLog.v("onSuccess(): " + str);
                        }

                        @Override
                        protected void onError() {
                            ToolLog.v("onError(): ");
                        }
                    });
        });
    }


    private void init(GoodsDetailParams p) {
//        private String appChannel; //GooglePlay
//        private String appKey; //android_lk98f83
//        private long appTimestamp; //1502694841229
//        private int appTypeId; //2
//        private String appVersion; //4.0
//        private String cookieId; //ee1fc8a0-d090-40f6-aaac-ee50898f1297
//        private String countryCode; //SA
//        private String currency; //SAR
//        private int lang; //0
//        private String sign; //7cd914f14c04c0b4497297207f4a1163
//        private int terminalType; //1
//        private int userId; //648002531
//        private String userToken; //dBWE9EvY6REsqg9+Uz4ExAQQ

        p.setAppChannel("GooglePlay");
        p.setAppKey("android_lk98f83");
        p.setAppTimestamp(1553070647469L);
        p.setAppTypeId(0);
        p.setAppVersion("7.8.3");
        p.setCookieId("8561c6a6-c3aa-4603-b034-888302573eb2");
        p.setCountryCode("SA");
        p.setCurrency("SAR");
        p.setDeviceId("bd5cbd41faed7895");
        p.setLang(0);
        p.setSign("57df80571ee07f502f32cdec42606f31");
        p.setTerminalType(1);
        p.setUserToken("dBWE9EvY6REsqg9+Uz4ExAQQ");

        // p.setUserId(648002531);

        // {"appChannel":"GooglePlay","appKey":"android_lk98f83","appTimestamp":"1553070647469"
        // ,"appTypeId":"0","appVersion":"7.8.3","cookieId":"8561c6a6-c3aa-4603-b034-888302573eb2"
        // ,"countryCode":"SA","currency":"SAR","deviceId":"bd5cbd41faed7895"
        // "lang":"0", "sign":"57df80571ee07f502f32cdec42606f31", "terminalType":"1",
        // , "userGender":0, "goodsId":14023,
    }


}












