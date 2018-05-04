package gww.testapp.ui.lambda;

import android.app.Activity;
import android.os.Bundle;

import gww.testapp.R;

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

        });
    }

}