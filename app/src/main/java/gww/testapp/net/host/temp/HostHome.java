package gww.testapp.net.host.temp;

import android.support.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


import gww.testapp.net.host.entity.ReadableApiBean;
import gww.testapp.net.host.enums.APIType;

/**
 * desc: 模拟HostHome Test <br/>
 * time: 2018/1/25 下午2:04 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class HostHome {

	@NonNull
	public ConcurrentMap<APIType, ReadableApiBean> createDefaultReadableApis() {
		ConcurrentMap<APIType, ReadableApiBean> apiBeanMap = new ConcurrentHashMap<>(5);
		putReadableApiBean(apiBeanMap, APIType.APP2, "http://app.default.jollychic.com");
		putReadableApiBean(apiBeanMap, APIType.H5, "http://h5.default.jollychic.com");
		putReadableApiBean(apiBeanMap, APIType.COUNTLY, "http://countly.jollychic.com");
		putReadableApiBean(apiBeanMap, APIType.SEARCH, "http://search.jollychic.com");
		putReadableApiBean(apiBeanMap, APIType.STYLE, "http://style.jollychic.com");
		return apiBeanMap;
	}

	private void putReadableApiBean(@NonNull Map<APIType, ReadableApiBean> apisBeanMap
			, @NonNull APIType apiType, @NonNull String defaultDomain) {
		apisBeanMap.put(apiType, new ReadableApiBean(apiType, defaultDomain));
	}

}
