package gww.testapp.net.okhttp3.request;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import gww.testapp.net.okhttp3.request.repo.model.FailureModel;
import gww.testapp.net.okhttp3.request.repo.FailureRepo;
import gww.testapp.net.okhttp3.request.repo.IRepo;
import gww.testapp.net.okhttp3.request.repo.SuccessRepo;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * desc: TODO <br/>
 * time: 2017/9/4 下午5:55 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */
public class MyOkHttp {

	// Http
	// Sign
	private OkHttpClient okHttpClient;
	private RequestCreator requestCreator;

	public MyOkHttp(RequestCreator requestCreator) {
		this.requestCreator = requestCreator;
	}

	public void setHttpClient(@NonNull OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
	}

	// get
	public void request4get(@NonNull String url) {
		this.requestByGet(url, null);
	}

	public IRepo requestByGet(@NonNull String url, Map<String, Object> paramsMap) {
		Request request = requestCreator.createRequest4get(url, paramsMap);
		return execute(request);
	}

	// form
	public IRepo requestByForm(@NonNull String url) {
		return requestByForm(url, null);
	}

	public IRepo requestByForm(@NonNull String url, Map<String, Object> paramsMap) {
		Request request = requestCreator.createRequest4Form(url, paramsMap);
		return execute(request);
	}

	// json
	public IRepo requestByJson(@NonNull String url) {
		return requestByJson(url, null);
	}

	public IRepo requestByJson(@NonNull String url, Map<String, Object> paramsMap) {
		Request request = requestCreator.createRequest4Json(url, paramsMap);
		return execute(request);
	}

	// upload
	public IRepo uploadFile(@NonNull String url, @NonNull MultipartBody.Part filePart, Map<String, Object> paramsMap) {
		return execute(requestCreator.createRequest4Upload(url, filePart, paramsMap));
	}

	public MultipartBody.Part createFilePart(String fileKey, @NonNull String filepath, @NonNull String mediaType) {
		File file = new File(filepath);
		RequestBody body = RequestBody.create(MediaType.parse(mediaType), file);
		return MultipartBody.Part.createFormData(fileKey, file.getName(), body);//TODO  file.getName() 反斜杠有bug；
	}

	public IRepo execute(Request request) {
		Call call = getHttpClient().newCall(request);

		try {
			return new SuccessRepo(call.execute());
		} catch (Exception ex) {
			FailureModel model = new FailureModel(request, ex);
			return new FailureRepo(model);
		}
	}

	@NonNull
	public OkHttpClient getHttpClient() {
		if (okHttpClient == null) {
			okHttpClient = new OkHttpClient.Builder()
					//.addInterceptor(getLogInterceptor()) TODO log
					.retryOnConnectionFailure(false)
					.connectTimeout(10, TimeUnit.SECONDS)
					.writeTimeout(10, TimeUnit.SECONDS)
					.readTimeout(10, TimeUnit.SECONDS)
					.build();
		}

		return okHttpClient;
	}

}
