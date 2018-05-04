package gww.testapp.net.okhttp3;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import gww.testapp.utils.ToolLog;
import gww.testapp.net.okhttp3.interceptor.HttpLogger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static android.content.ContentValues.TAG;

/**
 * desc: TODO <br/>
 * time: 2017/8/14 下午1:57 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */

public class OkHttp3Test {
	private final static String URL_SEARCH_GET = "http://192.168.2.118:8080/search/suggest?countryCode=JP&appVersion=6.8&lang=0&cookieId=c564a5bd-01d3-4e77-96cc-ad42d8333772&appChannel=GooglePlay&q=dre&language=en&currency=JPY&appTypeId=0&terminalType=1&invoker=app";


	private static void test() {
		// TODO Request header
		// 提供get、post-form、post-json、文件上传、文件下载

		// get
		Request request4Get = new Request.Builder().url("URL").build();

		// post - json
		MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(jsonMediaType, "json字符串");
		Request request4Json = new Request.Builder().url("URL").post(body).build();

		// post - form(表单)
		RequestBody formBody = new FormBody.Builder()
				.add("name", "liming")
				.add("school", "beida")
				.build();
		Request request4Form = new Request.Builder().url("URL").post(formBody).build();

		// post 上传
		MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
		Request requestUpload = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, new File("/sdcard/demo.txt")))
				.build();

		// post - String
		String postBodyString = "post body";
		Request request4String = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBodyString))//TODO MEDIA_TYPE_MARKDOWN 可能有问题
				.build();


		// post - Stream

		// post - MultipartBody

	}


	public static Interceptor getLogInterceptor() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return loggingInterceptor;
	}


	public static void get(String url, Map<String, String> params) {
		//TimeOut ?

	}

	// get
	public static void testGetOnMainTread() {
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(getLogInterceptor())
				.retryOnConnectionFailure(false)
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS)
				.readTimeout(10, TimeUnit.SECONDS)
				.build();

		Request request = new Request.Builder()
				.url(URL_SEARCH_GET)
				.build();
		Call call = okHttpClient.newCall(request);

		// TODO
		// call.cancel();
		// client.dispatcher().cancelAll();
		// List<Call> runningCalls =  client.dispatcher().runningCalls();
		// List<Call> queuedCalls = client.dispatcher().queuedCalls();

		try {
			Response response = call.execute();
			ToolLog.v("testGetOnMainTread() -> " + response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testGetOnBgThread() {
		OkHttpClient mHttpClient = new OkHttpClient();
		Request request = new Request.Builder().url(URL_SEARCH_GET).build();

		mHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				ToolLog.v("testGetOnBgThread() -> 失败。");
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				ToolLog.v("testGetOnBgThread() -> " + response.body().string());
			}
		});

		Call call = mHttpClient.newCall(request);
		call.cancel();

	}

	//post by json
	public static void testPostByJsonOnBgThread() {
		String url = "http://mvipapp.markatest.com/goods/getGoodsByIdNew.do";
		String json = "{\"appChannel\":\"GooglePlay\",\"appKey\":\"android_lk98f83\",\"appTimestamp\":\"1502694841229\",\"appTypeId\":\"2\",\"appVersion\":\"4.0\",\"cookieId\":\"ee1fc8a0-d090-40f6-aaac-ee50898f1297\",\"countryCode\":\"SA\",\"currency\":\"SAR\",\"goodsId\":1666661,\"lang\":\"0\",\"sign\":\"7cd914f14c04c0b4497297207f4a1163\",\"terminalType\":\"1\",\"userId\":\"648002531\",\"userToken\":\"dBWE9EvY6REsqg9+Uz4ExAQQ\"}";

		MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(jsonMediaType, json);
		Request request = new Request.Builder().url(url).post(body).build();

		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				ToolLog.v("testPostByJsonOnBgThread() -> " + response.body().string());
			}
		});
	}

	//post
	private void testPostByForm(String url, String json) throws IOException {
		OkHttpClient client = new OkHttpClient();
		RequestBody formBody = new FormBody.Builder()
				.add("name", "liming")
				.add("school", "beida")
				.build();

		Request request = new Request.Builder()
				.url(url)
				.post(formBody)
				.build();

		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String str = response.body().string();
			}
		});
	}

	// post by json
	public static void testPostByJsonOnMainThread() {
		String url = "http://mvipapp.markatest.com/goods/getGoodsByIdNew.do";
		String json = "{\"appChannel\":\"GooglePlay\",\"appKey\":\"android_lk98f83\",\"appTimestamp\":\"1502694841229\",\"appTypeId\":\"2\",\"appVersion\":\"4.0\",\"cookieId\":\"ee1fc8a0-d090-40f6-aaac-ee50898f1297\",\"countryCode\":\"SA\",\"currency\":\"SAR\",\"goodsId\":1666661,\"lang\":\"0\",\"sign\":\"7cd914f14c04c0b4497297207f4a1163\",\"terminalType\":\"1\",\"userId\":\"648002531\",\"userToken\":\"dBWE9EvY6REsqg9+Uz4ExAQQ\"}";

		MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(jsonMediaType, json);

		Request request = new Request.Builder().url(url).post(body).build();

		try {
			Response response = client.newCall(request).execute();
			ToolLog.v("testPostByJsonOnMainThread() -> " + response.body().string());

			//读取相应头
			ToolLog.v("testPostByJsonOnMainThread() server -> " + response.header("Server"));
			ToolLog.v("testPostByJsonOnMainThread() Date -> " + response.header("Date"));
			ToolLog.v("testPostByJsonOnMainThread() Vary -> " + response.header("Vary"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// post 上传文件
	private void postFileOnBgThread() {
		MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
		OkHttpClient mOkHttpClient = new OkHttpClient();
		File file = new File("/sdcard/demo.txt");
		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
				.build();


		mOkHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.i(TAG, response.body().string());
			}
		});
	}

	//ok get请求
	//ok POST提交Json数据
	//ok POST提交键值对
	//no post异步上传文件
	//no Post方式提交流
	//no Post方式提交分块请求 (MultipartBody)

	//no Post方式提交String
	//NO 响应缓存 ?

}
