package gww.testapp.ui.rxjava2.operators;

import gww.testapp.ui.rxjava2.model.User;
import gww.testapp.utils.ToolLog;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * distinct: 去重操作符 <br/>
 * time: 2018/5/9 下午4:09 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class RxDistinct implements IOperator {

    @Override
    public void test() {
       /*
       Observable.just(1, 1, 1, 2, 2, 3, 4)
                .distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                ToolLog.v("RxDistinct -> accept() i:" + integer);
            }
        });
        */

        // 比较的是对象，需要使用重写对象的equals()、hashCode()，否则distinct无效
        Observable.fromArray(new User("张三", 11)
                , new User("张三", 11), new User("李四", 12))
                .distinct()
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        ToolLog.v("RxDistinct -> accept() user.name:" + user.getName());
                    }
                });
    }

}
