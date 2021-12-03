package com.example.automed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.EditText;
import android.widget.TextView;

public class AlarmSettingActivity extends AppCompatActivity {

    // xml objects
    Button btApply;
    EditText etTimerMM1;
    EditText etTimerHH1;
    EditText etTimerMM2;
    EditText etTimerHH2;
    EditText etTimerMM3;
    EditText etTimerHH3;
    EditText etTimerMM4;
    EditText etTimerHH4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmsetting);

        // initializing variables
        etTimerMM1 = (EditText) findViewById(R.id.etTimerMM1);
        etTimerHH1 = (EditText) findViewById(R.id.etTimerHH1);
        etTimerMM2 = (EditText) findViewById(R.id.etTimerMM2);
        etTimerHH2 = (EditText) findViewById(R.id.etTimerHH2);
        etTimerMM3 = (EditText) findViewById(R.id.etTimerMM3);
        etTimerHH3 = (EditText) findViewById(R.id.etTimerHH3);
        etTimerMM4 = (EditText) findViewById(R.id.etTimerMM4);
        etTimerHH4 = (EditText) findViewById(R.id.etTimerHH4);
        btApply = (Button) findViewById(R.id.btApply);

        // default display in edittext
        etTimerHH1.setText(getHH(1));
        etTimerMM1.setText(getMM(1));
        etTimerHH2.setText(getHH(2));
        etTimerMM2.setText(getMM(2));
        etTimerHH3.setText(getHH(3));
        etTimerMM3.setText(getMM(3));
        etTimerHH4.setText(getHH(4));
        etTimerMM4.setText(getMM(4));

        // Setting button clicked
        btApply.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openAlarmStatusActivity();
                    }
                });
    }
    private void openAlarmStatusActivity() {
        Intent intent = new Intent(this, AlarmStatusActivity.class);
        startActivity(intent);
    }

    private String getHH(int alarmNum) {
        int HH = 0;
        // TODO: getting alarm HH from server
        return String.valueOf(HH);
    }

    private String getMM(int alarmNum) {
        int MM = 0;
        // TODO: getting alarm MM from server
        return String.valueOf(MM);
    }

}