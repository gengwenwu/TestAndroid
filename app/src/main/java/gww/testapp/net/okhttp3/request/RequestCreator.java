package gww.testapp.net.okhttp3.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * desc: TODO <br/>
 * time: 2017/9/4 下午5:42 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */
public class RequestCreator {

	private final static String MEDIA_TYPE_JSON = "application/json; charset=utf-8";

	public RequestCreator() {
		// TODO 初始化
		// 默认参数对象 DefaultParams
		// 签名对象 Sign
	}

	// get
	public Request createRequest4get(@NonNull String url, Map<String, Object> paramsMap) {
		StringBuffer urlSb = new StringBuffer(url.trim());
		Map<String, Object> finalParams = appendDefaultParams(paramsMap);
		String finalUrl = createFinalUrl4Get(urlSb, finalParams);
		return new Request.Builder().url(finalUrl).tag("UniqueId").build();//TODO  tag -> UniqueId
	}

	// form
	public Request createRequest4Form(@NonNull String url, Map<String, Object> paramsMap) {
		Map<String, Object> finalParams = appendDefaultParams(paramsMap);
		signRequestParams(url, finalParams);
		FormBody.Builder formBodyBuilder = new FormBody.Builder();
		for (String key : finalParams.keySet()) {
			if (key != null) {
				formBodyBuilder.add(key, (String) finalParams.get(key));
			}
		}

		return new Request.Builder()
				.url(url)
				.post(formBodyBuilder.build())
				.tag("UniqueId")//TODO  tag -> UniqueId
				.build();
	}

	// json
	public Request createRequest4Json(@NonNull String url, Map<String, Object> paramsMap) {
		Map<String, Object> finalParams = appendDefaultParams(paramsMap);
		signRequestParams(url, finalParams);
		MediaType jsonMediaType = MediaType.parse(MEDIA_TYPE_JSON);
		RequestBody body = RequestBody.create(jsonMediaType, JSONObject.toJSONString(finalParams));
		return new Request.Builder().url(url).post(body).tag("UniqueId").build();//TODO  tag -> UniqueId
	}

	// upload
	public Request createRequest4Upload(@NonNull String url, @NonNull MultipartBody.Part filePart, Map<String, Object> paramsMap) {
		Map<String, Object> finalParams = appendDefaultParams(paramsMap);
		signRequestParams(url, finalParams);

		MultipartBody.Builder builder = new MultipartBody.Builder();
		builder.setType(MultipartBody.FORM);
		builder.addPart(filePart);

		for (String key : finalParams.keySet()) {
			if (key != null) {
				builder.addFormDataPart(key, (String) finalParams.get(key));
			}
		}

		return new Request.Builder()
				.url(url)
				.post(builder.build())
				.tag("UniqueId")
				.build();//TODO  tag -> UniqueId;
	}


	// upload
	/*
	private static final String MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8";
	public Request createRequest4Upload(String url, String filePath) {
		MediaType markdownMediaType = MediaType.parse(MEDIA_TYPE_MARKDOWN);
		RequestBody.create(markdownMediaType, new File(filePath));
		RequestBody requestBody = RequestBody.create(markdownMediaType, new File(filePath));
		return  new Request.Builder()
				.url(url)
				.post(requestBody)
				.tag("UniqueId")
				.build();
	}*/

	//========================
	//========================
	//========================

	/**
	 * TODO 提取到DefaultParams 类中
	 * 添加请求必要参数
	 *
	 * @param customParamsMap 请求参数
	 * @return 所有请求参数Map
	 */
	@NonNull
	private Map<String, Object> appendDefaultParams(@Nullable Map<String, Object> customParamsMap) {
		if (customParamsMap == null) {
			customParamsMap = new HashMap<>();
		}

		// 将必要参数放在Map中
		ConcurrentHashMap<String, String> defaultParams = null;// TODO mRequestCreatorHelper.getDefaultRequestParams().getParamsMap();

		for (String key : defaultParams.keySet()) {
			if (null == customParamsMap.get(key)) {
				String defaultParamValue = defaultParams.get(key);

				if (!TextUtils.isEmpty(defaultParamValue)) {
					customParamsMap.put(key, defaultParamValue);
				}
			}
		}

		return customParamsMap;
	}


	/**
	 * 在url地址后面追加"?"符号
	 *
	 * @param sbUrl 请求url地址
	 */
	private void appendQuestionCharToUrl(@NonNull StringBuffer sbUrl) {
		if (sbUrl.toString().lastIndexOf("?") < 0) {
			sbUrl.append("?");
		}
	}

	/**
	 * 创建Get方式最终请求地址
	 *
	 * @param sbUrl     url地址
	 * @param paramsMap 参数Map
	 * @return url地址
	 */
	private String createFinalUrl4Get(@NonNull StringBuffer sbUrl, @NonNull Map<String, Object> paramsMap) {
		boolean isAppendParamSep = false;

		for (String key : paramsMap.keySet()) {
			if (!TextUtils.isEmpty(key) && paramsMap.get(key) != null) {
				/* TODO 临时注释
				String paramValue = ToolsText.trim(paramsMap.get(key).toString());

				if (!TextUtils.isEmpty(paramValue)) {
					sbUrl.append((isAppendParamSep ? "&" : ""));
					sbUrl.append(ToolsText.trim(key)); //key
					sbUrl.append("=");
					sbUrl.append(ToolsEncrypt.urlEncode(paramValue));// value
					isAppendParamSep = true;
				}*/
			}
		}

		return sbUrl.toString();
	}

	/**
	 * 签名
	 *
	 * @param url          请求地址
	 * @param allParamsMap 请求所有参数
	 */
	private void signRequestParams(String url, Map<String, Object> allParamsMap) {
		//TODO
		//String appVersion = mRequestCreatorHelper.getDefaultRequestParams()
		//		.getParamsMap().get(DefaultRequestParams.KEY_REQUEST_PARAM_APP_VERSION_NAME);
		//mRequestCreatorHelper.getSignBuilder().doSign(url, allParamsMap, appVersion);
	}

}
