package gww.testapp.net.host.entity;

import java.util.List;

/**
 * desc: 可读Api domain配置 <br/>
 * time: 2018/1/29 下午7:43 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class ReadableApiListBean { //TODO 序列化

	/**
	 * api类型
	 */
	private int apiType;
	/**
	 * 自定义通信地址
	 */
	private String domain;
	/**
	 * 使用{@link #domain} get方式请求接口
	 */
	private List<String> getApi;
	/**
	 * 使用{@link #domain}post方式请求接口
	 */
	private List<String> postApi;


	public int getApiType() {
		return apiType;
	}

	public void setApiType(int apiType) {
		this.apiType = apiType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<String> getGetApi() {
		return getApi;
	}

	public void setGetApi(List<String> getApi) {
		this.getApi = getApi;
	}

	public List<String> getPostApi() {
		return postApi;
	}

	public void setPostApi(List<String> postApi) {
		this.postApi = postApi;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("apiType=").append(apiType).append(", ");
		sb.append("domain=").append(domain).append(", ");

		/* getApi */
		sb.append("getApi=");
		if (getApi != null) {
			sb.append("[");
			for (String api : getApi) {
				sb.append(api);
				sb.append(", ");
			}

			sb.append("]");
		}
		sb.append(", ");

		/* postApi */
		sb.append("postApi=");
		if (postApi != null) {
			sb.append("[");
			for (String api : postApi) {
				sb.append(api);
				sb.append(", ");
			}

			sb.append("]");
		}

		return sb.toString();
	}

}
