package gww.testapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;

import gww.testapp.R;
import gww.testapp.net.host.ReadableApiManager;
import gww.testapp.net.host.entity.ReadableApiListContainerBean;
import gww.testapp.ui.lambda.ActivityLambda;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setClick();
    }

    private void setClick() {
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityB.class));
        });

        findViewById(R.id.btn_lambda).setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityLambda.class));
        });
    }

    private void testDomain() {
        String jsonStr = "{\"message\":\"Success\",\"messageCode\":\"0\",\"messageType\":0,\"readList\":[{\"apiType\":0,\"domain\":\"http://app.jollychic.com\",\"getApi\":[\"/other/getServerTimestamp.do\",\"/h5/help/applySellOnJC.do\"],\"postApi\":[\"/other/properties.do\",\"/other/getGoodsByIdNew.do\"]},{\"apiType\":1,\"domain\":\"http://h5.jollychic.com\",\"getApi\":[\"/1.html\",\"/2.html\"]}],\"result\":0}";
        ReadableApiListContainerBean bean = JSON.parseObject(jsonStr, ReadableApiListContainerBean.class);

		/*
        if (bean != null) {
			if (bean.getReadList() != null) {
				ToolLog.v("size:" + bean.getReadList().size());

				for (ReadableApiListBean item : bean.getReadList()) {
					ToolLog.v("item -> " + item.toString());
				}
			} else {
				ToolLog.v("ReadList is null");
			}
		}
		*/

        //click();
    }

    private void click() {
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            ReadableApiManager manager = ReadableApiManager.getInstance();

			/* */
            String jsonStr = "{\"message\":\"Success\",\"messageCode\":\"0\",\"messageType\":0,\"readList\":[{\"apiType\":0,\"domain\":\"http://app.jollychic.com\",\"getApi\":[\"/other/getServerTimestamp.do\",\"/h5/help/applySellOnJC.do\"],\"postApi\":[\"/other/properties.do\",\"/other/getGoodsByIdNew.do\"]},{\"apiType\":1,\"domain\":\"http://h5.jollychic.com\",\"getApi\":[\"/1.html\",\"/2.html\"]}],\"result\":0}";
            ReadableApiListContainerBean bean = JSON.parseObject(jsonStr, ReadableApiListContainerBean.class);
            manager.updateReadApiBeans(bean.getReadList());

			/*
            ToolLog.v("matchDomain(H5) ->" + manager.getDomain(Enums.APIType.H5));
			ToolLog.v("getDefaultDomain(H5) ->" + manager.getDefaultDomain(Enums.APIType.H5));
			ToolLog.v("getCustomDomain(H5) ->" + manager.getCustomDomain(Enums.APIType.H5));
			ToolLog.v("isApiTypeExists(COUNTLY) ->" + manager.isApiTypeExists(Enums.APIType.COUNTLY));
*/


			/*
            ToolLog.v("==> removeCustomApi(apiType, relativeUrl) begin");
			for (ReadableApiBean bean1 : manager.getReadableApiBeans().values()) {
				ToolLog.v("==> 原始：" + bean1);
			}

			ToolLog.v("================");

			manager.removeCustomApi(Enums.APIType.APP2, "/other/properties.do");
			manager.removeCustomApi(Enums.APIType.APP2, "/other/getServerTimestamp.do");
			manager.removeCustomApi(Enums.APIType.APP2, "/other/test.do");
			manager.removeCustomApi(Enums.APIType.H5, "/1.html");

			for (ReadableApiBean bean2 : manager.getReadableApiBeans().values()) {
				ToolLog.v("==> 结局：" + bean2);
			}

			ToolLog.v("==> removeCustomApi(apiType, relativeUrl) end");
 */


			/*ApiUrlCreator apiUrlCreator = new ApiUrlCreator();

			int apiTypeApp2 = Enums.APIType.APP2.getValue();
			String url1 = apiTypeApp2 + "/other/getServerTimestamp.do";
			String url2 = apiTypeApp2 + "other/getServerTimestamp.do";
			String url3 = "/other/getServerTimestamp.do";
			String url4 = apiTypeApp2 + "/goods/getGoodsDetail.do";

			ToolLog.v("url1 ->" + apiUrlCreator.createHttpUrl(manager, url1));
			ToolLog.v("url2 ->" + apiUrlCreator.createHttpUrl(manager, url2));
			ToolLog.v("url3 ->" + apiUrlCreator.createHttpUrl(manager, url3));
			ToolLog.v("url4 ->" + apiUrlCreator.createHttpUrl(manager, url4));

			ToolLog.v("===============");
			int apiTypeH5 = Enums.APIType.H5.getValue();
			String url11 = apiTypeH5 + "/1.html";
			String url12 = apiTypeH5 + "1.html";
			String url13 = apiTypeH5 + "/native/12.html";
			String url14 = apiTypeH5 + "native/13.html";
			ToolLog.v("url11 ->" + apiUrlCreator.createHttpUrl(manager, url11));
			ToolLog.v("url12 ->" + apiUrlCreator.createHttpUrl(manager, url12));
			ToolLog.v("url13 ->" + apiUrlCreator.createHttpUrl(manager, url13));
			ToolLog.v("url14 ->" + apiUrlCreator.createHttpUrl(manager, url14));*/

/*
            final int size = 10;

			new Thread(() -> {
				for (int i = 0; i < size; i++) {
					ReadableApiManager.getInstance();
				}
			}).start();

			new Thread(() -> {
				for (int i = 0; i < size; i++) {
					ReadableApiManager.getInstance();
				}
			}).start();

			new Thread(() -> {
				for (int i = 0; i < size; i++) {
					ReadableApiManager.getInstance();
				}
			}).start();


			for (int i = 0; i < size; i++) {
				new Thread(() -> {
					for (int j = 0; j < size; j++) {
						ReadableApiManager.getInstance();
					}
				}).start();
			}

			*/

        });
    }

}
