package gww.testapp.net.host.enums;

import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * desc: TODO <br/>
 * time: 2018/2/2 下午2:45 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */

public enum APIType {

	APP2(0), H5(1), SEARCH(2), STYLE(3), COUNTLY(4);

	int value;

	APIType(int value) {
		this.value = value;
	}

	/**
	 * 查找 {@link APIType}类型
	 *
	 * @param apiTypeValue {@link APIType#getValue()}值
	 */
	@Nullable
	public APIType findApiType(int apiTypeValue) {
		for (APIType apiType : values()) {
			if (apiType.value == apiTypeValue) {
				return apiType;
			}
		}

		return null;
	}

	/**
	 * 查找 {@link APIType}类型
	 *
	 * @param apiTypeValueStr {@link APIType#getValue()}值
	 */
	@Nullable
	public APIType findApiType(String apiTypeValueStr) {
		return findApiType(parseApiTypeValue(apiTypeValueStr));
	}

	/**
	 * 解析ApiType int值
	 */
	private int parseApiTypeValue(String apiTypeStr) {
		final int unknownApiType = -1;

		try {
			if (!TextUtils.isEmpty(apiTypeStr)) {
				return Integer.parseInt(apiTypeStr);
			}

			return unknownApiType;
		} catch (Exception e) {
			return unknownApiType;
		}
	}

	public int getValue() {
		return value;
	}

}
