package gww.testapp.net.host.entity;

import android.support.annotation.NonNull;

import java.util.concurrent.ConcurrentMap;

import gww.testapp.net.host.enums.APIType;

/**
 * desc: 可读Api配置 <br/>
 * time: 2018/1/24 上午11:18 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class ReadableApiBean { //TODO 序列化

	/**
	 * api类型
	 */
	private APIType apiType;
	/**
	 * 默认通信地址
	 */
	private String defaultDomain;
	/**
	 * 自定义通信地址
	 */
	private String customDomain;
	/**
	 * post api集合。<br/>
	 * key:/goods/history.do, value: true
	 */
	private ConcurrentMap<String, Boolean> postApis;
	/**
	 * get Api集合。<br/>
	 * key:/goods/history.do, value: true
	 */
	private ConcurrentMap<String, Boolean> getApis;


	public ReadableApiBean() {

	}

	public ReadableApiBean(@NonNull APIType apiType, @NonNull String defaultDomain) {
		this.apiType = apiType;
		this.defaultDomain = defaultDomain;
	}

	@NonNull
	public APIType getApiType() {
		return apiType;
	}

	public void setApiType(@NonNull APIType apiType) {
		this.apiType = apiType;
	}

	@NonNull
	public String getDefaultDomain() {
		return defaultDomain;
	}

	public void setDefaultDomain(@NonNull String defaultDomain) {
		this.defaultDomain = defaultDomain;
	}

	public String getCustomDomain() {
		return customDomain;
	}

	public void setCustomDomain(String customDomain) {
		this.customDomain = customDomain;
	}

	public ConcurrentMap<String, Boolean> getPostApis() {
		return postApis;
	}

	public void setPostApis(ConcurrentMap<String, Boolean> postApis) {
		this.postApis = postApis;
	}

	public ConcurrentMap<String, Boolean> getGetApis() {
		return getApis;
	}

	public void setGetApis(ConcurrentMap<String, Boolean> getApis) {
		this.getApis = getApis;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("apiType=").append(apiType).append(", ");
		sb.append("defaultDomain=").append(defaultDomain).append(", ");
		sb.append("customDomain=").append(customDomain).append(", ");

		/* getApi */
		sb.append("postApis=");
		if (postApis != null) {
			sb.append("[");
			for (String api : postApis.keySet()) {
				sb.append(api);
				sb.append(", ");
			}

			sb.append("]");
		}
		sb.append(", ");

		/* postApi */
		sb.append("getApis=");
		if (getApis != null) {
			sb.append("[");
			for (String api : getApis.keySet()) {
				sb.append(api);
				sb.append(", ");
			}

			sb.append("]");
		}

		return sb.toString();
	}

}