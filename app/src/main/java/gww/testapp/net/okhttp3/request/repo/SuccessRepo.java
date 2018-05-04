package gww.testapp.net.okhttp3.request.repo;

import okhttp3.Response;

/**
 * desc: TODO <br/>
 * time: 2017/9/5 下午5:51 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */
public class SuccessRepo implements IRepo<Response> {

	private Response response;

	public SuccessRepo(Response response) {
		this.response = response;
	}

	@Override
	public Response getRepo() {
		return response;
	}

}