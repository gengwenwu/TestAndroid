package gww.testapp.net.host;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import gww.testapp.net.host.entity.RelativeUrlBean;
import gww.testapp.net.host.enums.APIType;
import gww.testapp.utils.ToolLog;

/**
 * desc: 通信Url创造器 <br/>
 * time: 2018/1/24 下午2:37 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class ApiUrlCreator {

	@NonNull
	public String createHttpUrl(@NonNull ReadableApiManager readableApiManager, @NonNull String relativeUrl) {
		if (checkRelativeUrl(relativeUrl)) {
			RelativeUrlBean relativeUrlBean = createRelativeUrlBean(relativeUrl);

			if (checkRelativeUrlBean(relativeUrlBean, relativeUrl)) {
				return readableApiManager.matchDomain(relativeUrlBean) + relativeUrlBean.getRelativeUrl();
			}
		}

		return relativeUrl;
	}

	private boolean checkRelativeUrl(String relativeUrl) {
		if (TextUtils.isEmpty(relativeUrl)
				|| relativeUrl.startsWith("http")
				|| relativeUrl.startsWith("/")) {
			// TODO 抛出异常，输出相对地址信息
			ToolLog.e("checkRelativeUrl() -> relativeUrl格式错误：" + relativeUrl);
			return false;
		}

		return true;
	}

	@NonNull
	private RelativeUrlBean createRelativeUrlBean(@NonNull String relativeUrl) {
		String apiTypeStr;
		String realRelativeUrl;
		int index = relativeUrl.indexOf('/');

		if (index < 0) {
			apiTypeStr = null;
			realRelativeUrl = relativeUrl;
		} else {
			apiTypeStr = relativeUrl.substring(0, index);
			realRelativeUrl = relativeUrl.substring(index, relativeUrl.length());
		}

		APIType apiType = APIType.APP2.findApiType(apiTypeStr);
		return new RelativeUrlBean(apiType, realRelativeUrl);
	}


	private boolean checkRelativeUrlBean(@NonNull RelativeUrlBean relativeUrlBean, @NonNull String relativeUrl) {
		// TODO 只在正式站才check
		if (relativeUrlBean.getApiType() == null) {
			// 抛出异常，输出相对地址信息
			ToolLog.e("checkRelativeUrlBean() -> ApiType为null, 原始地址:" + relativeUrl);
			return false;
		}

		if (TextUtils.isEmpty(relativeUrlBean.getRelativeUrl())
				|| (!relativeUrlBean.getRelativeUrl().startsWith("/"))) {
			// TODO 抛出异常，输出相对地址信息
			ToolLog.e("checkRelativeUrlBean() -> relativeUrl格式错误："
					+ relativeUrlBean.getRelativeUrl() + ", 原始地址：" + relativeUrl);
			return false;
		}

		return true;
	}

}
