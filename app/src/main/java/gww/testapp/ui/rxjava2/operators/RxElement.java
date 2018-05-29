package gww.testapp.ui.rxjava2.operators;

import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * desc: Element相关操作符 <br/>
 * 1，elementAt(index, defaultItem)：取发送数据序列中第n个数据，序列号从0开始，越界可设置默认值。<br/>
 * 2，elementAtOrError()， 与elementAt类似，但是越界会抛出异常。<br/>
 * 3，firstElement()，取第一个元素。<br/>
 * 4，lastElement()，取最后一个元素。<br/>
 * time: 2018/5/29 上午11:25 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxElement implements IOperator {


    @Override
    public void test() {
        String[] array = {"A", "B", "C", "D"};
        testElementAt(array);
        testElementAtOrError(array);
        testFirstElement(array);
        testLastElement(array);
    }

    /**
     * 测试 elementAt(index, defaultItem)
     */
    private void testElementAt(String[] array) {
        Observable.fromArray(array)
                .elementAt(2, "Default") // 下标越界使用"Default"
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToolLog.v("RxElement -> testElementAt(), s:" + s);
                    }
                });
    }

    /**
     * 测试 elementAtOrError(index)
     */
    private void testElementAtOrError(String[] array) {
        Observable.fromArray(array)
                .elementAtOrError(3)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToolLog.v("RxElement -> testElementAtOrError(), s:" + s);
                    }
                });
    }

    /**
     * 测试 firstElement()
     */
    private void testFirstElement(String[] array) {
        Observable.fromArray(array)
                .firstElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToolLog.v("RxElement -> firstElement(), s:" + s);
                    }
                });
    }

    /**
     * 测试 lastElement()
     */
    private void testLastElement(String[] array) {
        Observable.fromArray(array)
                .lastElement()
                .subscribe(s -> ToolLog.v("RxElement -> testLastElement(), s:" + s));
    }

}