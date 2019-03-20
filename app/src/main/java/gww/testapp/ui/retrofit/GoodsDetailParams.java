package gww.testapp.ui.retrofit;

import gww.testapp.ui.retrofit.api.RequestParams;

/**
 * desc: TODO <br/>
 * time: 2019/3/20 下午3:29 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class GoodsDetailParams extends RequestParams {

    public GoodsDetailParams() {

    }

    public GoodsDetailParams(int goodsId) {
        this.goodsId = goodsId;
    }

    private int goodsId;



    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }


}
