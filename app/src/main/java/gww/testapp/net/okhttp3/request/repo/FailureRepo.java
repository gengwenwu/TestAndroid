package gww.testapp.net.okhttp3.request.repo;

import gww.testapp.net.okhttp3.request.repo.model.FailureModel;

/**
 * desc: TODO <br/>
 * time: 2017/9/5 下午5:52 <br/>
 * author: 居廉 <br/>
 * since V TODO <br/>
 */
public class FailureRepo implements IRepo<FailureModel> {

	private FailureModel failureModel;

	public FailureRepo(FailureModel failureModel) {
		this.failureModel = failureModel;

	}

	@Override
	public FailureModel getRepo() {
		return failureModel;
	}

}
