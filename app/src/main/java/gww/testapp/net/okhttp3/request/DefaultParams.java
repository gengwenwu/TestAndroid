package gww.testapp.net.okhttp3.request;

import android.content.Context;

import java.util.concurrent.ConcurrentHashMap;

/**
 * desc: TODO <br/>
 * time: 2017/9/4 下午5:48 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */

public class DefaultParams {


	private Context mApplicationCtx;

	public DefaultParams(Context ctx) {
		this.mApplicationCtx = ctx.getApplicationContext();
	}

	/**
	 * 请求必要参数Key：多语言
	 */
	private static final String KEY_REQUEST_PARAM_LANG = "lang";
	/**
	 * 请求必要参数Key：userToken
	 */
	private static final String KEY_REQUEST_PARAM_USER_TOKEN = "userToken";

	/**
	 * 请求必要参数Key：App版本名称。如：3.0.7
	 */
	private static final String KEY_REQUEST_PARAM_APP_VERSION_NAME = "appVersion";


	/**
	 * 默认请求参数集合
	 */
	private ConcurrentHashMap<String, String> mDefaultParamsMap;


	public ConcurrentHashMap<String, String> getParamsMap() {
		if (mDefaultParamsMap == null || mDefaultParamsMap.isEmpty()) {
			mDefaultParamsMap = createDefaultParamsMap(mApplicationCtx);
		}

		return mDefaultParamsMap;
	}

	/**
	 * 创建默认参数Map
	 */
	private ConcurrentHashMap<String, String> createDefaultParamsMap(Context applicationCtx) {
		ConcurrentHashMap<String, String> defaultParamsMap = new ConcurrentHashMap<>(8);

		defaultParamsMap.put(KEY_REQUEST_PARAM_LANG, "en");
		defaultParamsMap.put(KEY_REQUEST_PARAM_USER_TOKEN, "TODO");
		defaultParamsMap.put(KEY_REQUEST_PARAM_APP_VERSION_NAME, "1.0");
		//setFixedParams(defaultParamsMap, applicationCtx);
		//setInstantParams(defaultParamsMap, applicationCtx);
		return defaultParamsMap;
	}

}
