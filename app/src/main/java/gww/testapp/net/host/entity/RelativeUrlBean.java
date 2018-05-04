package gww.testapp.net.host.entity;

import gww.testapp.net.host.enums.APIType;

/**
 * desc: 相对地址 <br/>
 * time: 2018/1/24 下午1:35 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class RelativeUrlBean { //TODO 序列化

	/**
	 * api类型
	 */
	private APIType apiType;
	/**
	 * 相对地址
	 */
	private String relativeUrl;


	public RelativeUrlBean(APIType apiType, String relativeUrl) {
		this.apiType = apiType;
		this.relativeUrl = relativeUrl;
	}


	public APIType getApiType() {
		return apiType;
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

}