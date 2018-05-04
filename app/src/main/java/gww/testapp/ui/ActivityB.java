package gww.testapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import gww.testapp.R;

/**
 * desc: TODO <br/>
 * time: 2018/3/26 下午4:42 <br/>
 * author: Logan <br/>
 * since V TODO <br/>
 */
public class ActivityB extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        findViewById(R.id.btn_b).setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityC.class));
        });
    }

}
