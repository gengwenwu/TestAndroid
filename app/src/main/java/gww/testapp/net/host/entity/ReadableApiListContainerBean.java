package gww.testapp.net.host.entity;

import java.util.List;

/**
 * desc: 可读Api domain配置 容器类 <br/>
 * time: 2018/1/29 下午7:47 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class ReadableApiListContainerBean { //TODO 序列化

	private List<ReadableApiListBean> readList;

	public List<ReadableApiListBean> getReadList() {
		return readList;
	}

	public void setReadList(List<ReadableApiListBean> readList) {
		this.readList = readList;
	}

}
