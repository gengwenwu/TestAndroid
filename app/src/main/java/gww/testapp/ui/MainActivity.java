package gww.testapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import gww.testapp.R;
import gww.testapp.ui.lambda.ActivityLambda;
import gww.testapp.ui.rxjava2.Activity_1_SimpleDemo;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setClick();
    }

    private void setClick() {
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityB.class));
        });

        findViewById(R.id.btn_lambda).setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityLambda.class));
        });

        findViewById(R.id.btn_rxjava2_simple_demo).setOnClickListener(v -> {
            startActivity(new Intent(this, Activity_1_SimpleDemo.class));
        });
    }


}
