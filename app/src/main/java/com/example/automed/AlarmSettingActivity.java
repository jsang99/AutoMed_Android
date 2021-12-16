package com.example.automed;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
    Switch swAlarm1;
    Switch swAlarm2;
    Switch swAlarm3;
    Switch swAlarm4;
    String newStatus1, newStatus2, newStatus3, newStatus4;
    String newHour1, newHour2, newHour3, newHour4;
    String newMin1, newMin2, newMin3, newMin4;

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
        swAlarm1 = (Switch) findViewById(R.id.swTime1);
        swAlarm2 = (Switch) findViewById(R.id.swTime2);
        swAlarm3 = (Switch) findViewById(R.id.swTime3);
        swAlarm4 = (Switch) findViewById(R.id.swTime4);

        // default display in edittext
        etTimerHH1.setText(AlarmStatusActivity.hour1);
        etTimerMM1.setText(AlarmStatusActivity.min1);
        etTimerHH2.setText(AlarmStatusActivity.hour2);
        etTimerMM2.setText(AlarmStatusActivity.min2);
        etTimerHH3.setText(AlarmStatusActivity.hour3);
        etTimerMM3.setText(AlarmStatusActivity.min3);
        etTimerHH4.setText(AlarmStatusActivity.hour4);
        etTimerMM4.setText(AlarmStatusActivity.min4);

        // setting switch texts
        swAlarm1.setTextOn("On");
        swAlarm1.setTextOff("Off");
        swAlarm1.setShowText(true);
        swAlarm2.setTextOn("On");
        swAlarm2.setTextOff("Off");
        swAlarm2.setShowText(true);
        swAlarm3.setTextOn("On");
        swAlarm3.setTextOff("Off");
        swAlarm3.setShowText(true);
        swAlarm4.setTextOn("On");
        swAlarm4.setTextOff("Off");
        swAlarm4.setShowText(true);

        // Setting button clicked
        btApply.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendData2Server();

                    }
                });
        // setting default switch state
        if ((!swAlarm1.isChecked()) && !(AlarmStatusActivity.status1.equals("0"))){
            swAlarm1.setChecked(true);
        }
        if ((!swAlarm2.isChecked()) && !(AlarmStatusActivity.status2.equals("0"))){
            swAlarm2.setChecked(true);
        }
        if ((!swAlarm3.isChecked()) && !(AlarmStatusActivity.status3.equals("0"))){
            swAlarm3.setChecked(true);
        }
        if ((!swAlarm4.isChecked()) && !(AlarmStatusActivity.status4.equals("0"))){
            swAlarm4.setChecked(true);
        }
    }
    private void sendData2Server() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    // getting entered data
                    if (swAlarm1.isChecked()) { newStatus1 = "1"; } else { newStatus1 = "0"; }
                    if (swAlarm2.isChecked()) { newStatus2 = "1"; } else { newStatus2 = "0"; }
                    if (swAlarm3.isChecked()) { newStatus3 = "1"; } else { newStatus3 = "0"; }
                    if (swAlarm4.isChecked()) { newStatus4 = "1"; } else { newStatus4 = "0"; }
                    newHour1 = etTimerHH1.getText().toString();
                    newHour2 = etTimerHH2.getText().toString();
                    newHour3 = etTimerHH3.getText().toString();
                    newHour4 = etTimerHH4.getText().toString();
                    newMin1 = etTimerMM1.getText().toString();
                    newMin2 = etTimerMM2.getText().toString();
                    newMin3 = etTimerMM3.getText().toString();
                    newMin4 = etTimerMM4.getText().toString();

                    String url;
                    //set_time?time_1=1-22-30&time_2=1-22-30&time_3=1-22-30&time_4=1-22-30
                    url = "http://" + WelcomeActivity.IP_address + "/set_time?" +
                            "time_1=" + newStatus1 + "-" +newHour1 + "-" + newMin1 + "&" +
                            "time_2=" + newStatus2 + "-" +newHour2 + "-" + newMin2 + "&" +
                            "time_3=" + newStatus3 + "-" +newHour3 + "-" + newMin3 + "&" +
                            "time_4=" + newStatus4 + "-" +newHour4 + "-" + newMin4;
                    Document doc = Jsoup.connect(url).get();
                    Element body = doc.body();
                    builder.append(body.text());
                } catch (Exception e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //result.setText(builder.toString());
                        openAlarmStatusActivity();
                        //AlarmStatusActivity.allTime = builder.toString();
                        //AlarmStatusActivity.parseAllTime();
                        Context context = getApplicationContext();
                        Toast.makeText(context, builder.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }
    private void openAlarmStatusActivity() {
        Intent intent = new Intent(this, AlarmStatusActivity.class);
        startActivity(intent);
    }
}