package gww.testapp.net.okhttp3.request.repo.model;

import okhttp3.Request;

/**
 * desc: TODO <br/>
 * time: 2017/9/5 下午7:01 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */

public class FailureModel {

	private Request request;
	private Exception exception;

	public FailureModel(Request request, Exception ex) {
		this.request = request;
		this.exception = ex;
	}

	public Request getRequest() {
		return request;
	}

	public Exception getException() {
		return exception;
	}

}
