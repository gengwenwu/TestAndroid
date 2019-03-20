package gww.testapp.net.okhttp3;

/**
 * desc: 时间记录器 <br/>
 * time: 2019/3/20 下午1:49 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class TimeWatch {

    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 结束时间
     */
    private long endTime;


    /**
     * 开始计时
     */
    public void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * 结束计时
     */
    public TimeWatch stop() {
        endTime = System.currentTimeMillis();
        return this;
    }

    /**
     * 使用时长(秒)
     */
    public long getElapsedTime() {
        if (endTime > 0) {
            return (endTime - startTime) / 1000;
        } else {
            return 0;
        }
    }

}
