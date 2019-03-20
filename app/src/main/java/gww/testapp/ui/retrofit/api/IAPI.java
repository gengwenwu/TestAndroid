package gww.testapp.ui.retrofit.api;


import gww.testapp.ui.retrofit.GoodsDetailParams;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * desc: TODO <br/>
 * time: 2019/3/20 下午2:58 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public interface IAPI {

    // http://weeklyapp.jollytest.com/
    // v1/goodsDetail/main.do
    @POST("v1/goodsDetail/main.do")
    Observable<String> query(@Body GoodsDetailParams params);

}
