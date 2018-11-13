package gww.testapp.ui.lambda;

import android.app.Activity;
import android.os.Bundle;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import gww.testapp.R;
import gww.testapp.data.SimpleData;
import gww.testapp.ui.rxjava2.model.User;
import gww.testapp.utils.ToolLog;

/**
 * desc: Lambda 测试 <br/>
 * time: 2018/4/17 上午10:48 <br/>
 * author: Logan <br/>
 * since V 1.0 <br/>
 */
public class ActivityLambda extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);

        findViewById(R.id.btn_test).setOnClickListener(v -> {
            // 官方：Lightweight-Stream-API
            // https://github.com/aNNiMON/Lightweight-Stream-API

            // demo1
            Stream.of(getUsers())
                    // 官方写法：user.getCars().stream()
                    .flatMap(user -> Stream.of(user.getCars()))
                    .forEach(car ->
                            ToolLog.e(car.getBrand() + ":" + car.getName())
                    );

            // demo2
            int max1 = Stream.of(1, 2, 3)
                    .mapToInt(integer -> integer)
                    .max()
                    .getAsInt();
            ToolLog.e("Max Numbver：" + max1);

//            // 官方
//            int max2 = java.util.stream.Stream.of(1, 2, 3)
//                    .mapToInt(integer -> integer)
//                    .summaryStatistics()
//                    .getMax();
        });

    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(2);
        users.add(SimpleData.USER_ZHANG_SAN);
        users.add(SimpleData.USER_LI_SI);
        return users;
    }

}