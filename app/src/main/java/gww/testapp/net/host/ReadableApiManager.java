package gww.testapp.net.host;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import gww.testapp.ApplicationMain;
import gww.testapp.net.host.entity.ReadableApiBean;
import gww.testapp.net.host.entity.ReadableApiListBean;
import gww.testapp.net.host.entity.RelativeUrlBean;
import gww.testapp.net.host.enums.APIType;
import gww.testapp.net.host.temp.HostHome;
import gww.testapp.net.host.temp.ToolPreferences;

/**
 * desc: 可读Api配置管理类 <br/>
 * time: 2018/1/24 下午2:48 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class ReadableApiManager {

	/**
	 * domain配置本地SharedPreferences文件
	 */
	private static final String SP_FILE_NAME = "readable_api_map.dat";
	/**
	 * domain配置本地ConcurrentMap<Enums.APIType, ReadableApiBean¬>缓存数据
	 */
	private static final String KEY_READABLE_API_MAP = "key_readable_api_map";


	private static ReadableApiManager INSTANCE;


	/**
	 * 通过 {@link #getReadableApiBeans()}获取该对象，防止为null
	 */
	private ConcurrentMap<APIType, ReadableApiBean> mReadableApiBeanMap;


	private ReadableApiManager() {
		getReadableApiBeans();
	}


	public static synchronized ReadableApiManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ReadableApiManager();
		}

		return INSTANCE;
	}

	public synchronized void updateReadApiBeans(List<ReadableApiListBean> readableApiList) {
		if ((readableApiList != null) && (!readableApiList.isEmpty())) {
			updateMemoryReadableApiBeans(createReadableApiMap(readableApiList));
			saveReadableApiBeansToLocal(ApplicationMain.mInstance, getReadableApiBeans());
		}
	}

	@NonNull
	private Map<APIType, ReadableApiBean> createReadableApiMap(@NonNull List<ReadableApiListBean> readableApiList) {
		final Map<APIType, ReadableApiBean> readableApiBeanMap = new HashMap<>(readableApiList.size());

		for (ReadableApiListBean bean : readableApiList) {
			APIType apiType = APIType.APP2.findApiType(bean.getApiType());

			if (apiType != null) {
				readableApiBeanMap.put(apiType, createReadableApiBean(bean, apiType));
			}
		}

		return readableApiBeanMap;
	}

	@NonNull
	private ReadableApiBean createReadableApiBean(@NonNull ReadableApiListBean apiBean, @NonNull APIType apiType) {
		ReadableApiBean bean = new ReadableApiBean(apiType, apiBean.getDomain());
		bean.setCustomDomain(apiBean.getDomain());
		bean.setGetApis(createApiConcurrentMap(apiBean.getGetApi()));
		bean.setPostApis(createApiConcurrentMap(apiBean.getPostApi()));
		return bean;
	}

	@Nullable
	private ConcurrentMap<String, Boolean> createApiConcurrentMap(@Nullable List<String> apiList) {
		if ((apiList != null) && (!apiList.isEmpty())) {
			ConcurrentMap<String, Boolean> map = new ConcurrentHashMap<>(apiList.size());

			for (String api : apiList) {
				if (!TextUtils.isEmpty(api) && api.startsWith("/")) {
					map.put(api, true);
				}
			}

			return map;
		}

		return null;
	}

	private void updateMemoryReadableApiBeans(@NonNull Map<APIType, ReadableApiBean> readableApiBeanMap) {
		for (APIType apiType : readableApiBeanMap.keySet()) {
			ReadableApiBean newApiBean = readableApiBeanMap.get(apiType);
			ReadableApiBean oldApiBean = getReadableApiBeans().get(apiType);

			/* 更新配置 */
			if (oldApiBean != null && newApiBean != null) {
				oldApiBean.setCustomDomain(newApiBean.getCustomDomain());
				oldApiBean.setPostApis(newApiBean.getPostApis());
				oldApiBean.setGetApis(newApiBean.getGetApis());
			}
		}
	}

	private void saveReadableApiBeansToLocal(@NonNull Context ctx, Map<APIType, ReadableApiBean> readableApiMap) {
		String apiBeanJsonStr = (readableApiMap == null || readableApiMap.isEmpty()) ? null : JSON.toJSONString(readableApiMap);
		ToolPreferences sharedPref = new ToolPreferences();
		sharedPref.setString(ctx, SP_FILE_NAME, KEY_READABLE_API_MAP, apiBeanJsonStr);
		sharedPref.apply();
	}

	public boolean isApiTypeExists(@NonNull APIType apiType) {
		return getReadableApiBeans().get(apiType) != null;
	}

	@Nullable
	public String getDefaultDomain(@NonNull APIType apiType) {
		ReadableApiBean apiBean = getReadableApiBeans().get(apiType);
		return (apiBean != null) ? apiBean.getDefaultDomain() : null;
	}

	@Nullable
	public String getCustomDomain(@NonNull APIType apiType) {
		ReadableApiBean apiBean = getReadableApiBeans().get(apiType);
		return (apiBean != null) ? apiBean.getCustomDomain() : null;
	}

	@Nullable
	public String getDomain(APIType apiType) {
		ReadableApiBean apiBean = getReadableApiBeans().get(apiType);

		if (apiBean != null) {
			return TextUtils.isEmpty(apiBean.getCustomDomain()) ? apiBean.getDefaultDomain() : apiBean.getCustomDomain();
		}

		// TODO apiType可能有问题，发生错误了。
		return null;
	}

	public void removeCustomApi(@NonNull APIType apiType, @NonNull String relativeUrl) {
		ReadableApiBean apiBean = getReadableApiBeans().get(apiType);

		if (apiBean != null) {
			if (apiBean.getPostApis() != null) {
				apiBean.getPostApis().remove(relativeUrl);
			}

			if (apiBean.getGetApis() != null) {
				apiBean.getGetApis().remove(relativeUrl);
			}
		}
	}

	@Nullable
	String matchDomain(@NonNull RelativeUrlBean urlBean) {
		ReadableApiBean apiBean = getReadableApiBeans().get(urlBean.getApiType());

		if ((apiBean != null)) {
			if ((apiBean.getPostApis() != null)
					&& apiBean.getPostApis().containsKey(urlBean.getRelativeUrl())) {
				// 匹配了 Post api
				return apiBean.getCustomDomain();
			} else if ((apiBean.getGetApis() != null)
					&& apiBean.getGetApis().containsKey(urlBean.getRelativeUrl())) {
				// 匹配了 get api
				return apiBean.getCustomDomain();
			}

			return apiBean.getDefaultDomain();
		}

		//TODO 走到这里就异常
		return null;
	}

	@NonNull
	private synchronized Map<APIType, ReadableApiBean> getReadableApiBeans() {
		if (mReadableApiBeanMap == null || mReadableApiBeanMap.isEmpty()) {
			// TODO new HostHome() 写死
			ConcurrentMap<APIType, ReadableApiBean> defaultApiBeanMap = new HostHome().createDefaultReadableApis();
			mReadableApiBeanMap = loadReadableApiBeans(ApplicationMain.mInstance, defaultApiBeanMap);
		}

		return mReadableApiBeanMap;
	}

	@NonNull
	private ConcurrentMap<APIType, ReadableApiBean> loadReadableApiBeans(@NonNull Context ctx
			, ConcurrentMap<APIType, ReadableApiBean> defaultApisMap) {
		ConcurrentMap<APIType, ReadableApiBean> localApisMap = loadLocalReadableApiBeans(ctx);

		if ((localApisMap == null) || localApisMap.isEmpty()) {
			/* 首次 */
			return defaultApisMap;
		} else {
			/* 有本地记录 */
			changeDefaultReadableApiBeans(localApisMap, defaultApisMap);
			return defaultApisMap;
		}
	}

	private void changeDefaultReadableApiBeans(@NonNull ConcurrentMap<APIType, ReadableApiBean> localApisMap
			, @NonNull ConcurrentMap<APIType, ReadableApiBean> defaultApisMap) {
		for (APIType apiType : defaultApisMap.keySet()) {
			ReadableApiBean defaultBean = defaultApisMap.get(apiType);
			ReadableApiBean localBean = localApisMap.remove(apiType);

			if ((defaultBean != null) && (localBean != null)) {
				/* 保证必要信息有数据 */
				localBean.setApiType(defaultBean.getApiType());
				localBean.setDefaultDomain(defaultBean.getDefaultDomain());
				/* local放入default */
				defaultApisMap.put(apiType, localBean);
			}
		}

		if (localApisMap.size() > 0) {
			//TODO 输出异常
		}
	}

	@Nullable
	private ConcurrentMap<APIType, ReadableApiBean> loadLocalReadableApiBeans(@NonNull Context ctx) {
		ConcurrentMap<APIType, ReadableApiBean> readableApisMap = null;

		try {
			ToolPreferences sharedPref = new ToolPreferences();
			String apisBeanJsonStr = sharedPref.getString(ctx, SP_FILE_NAME, KEY_READABLE_API_MAP, "");

			if (!TextUtils.isEmpty(apisBeanJsonStr)) {
				readableApisMap = JSON.parseObject(apisBeanJsonStr
						, new TypeReference<ConcurrentHashMap<APIType, ReadableApiBean>>() {
						});
			}
		} catch (Exception e) {
			// TODO 输出异常
			e.printStackTrace();
		}

		return readableApisMap;
	}

}
