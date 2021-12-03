package com.example.automed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AlarmStatusActivity extends AppCompatActivity{
    // xml objects
    Button btSetting;
    SwipeRefreshLayout pullToRefresh;
    TextView tvTimeDisplay1;
    TextView tvTimeDisplay2;
    TextView tvTimeDisplay3;
    TextView tvTimeDisplay4;
    TextView tvStatus1;
    TextView tvStatus2;
    TextView tvStatus3;
    TextView tvStatus4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmstatus);

        // initializing variables
        btSetting = (Button) findViewById(R.id.btSetting);
        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        tvTimeDisplay1 = (TextView) findViewById(R.id.tvTimeDisplay1);
        tvTimeDisplay2 = (TextView) findViewById(R.id.tvTimeDisplay2);
        tvTimeDisplay3 = (TextView) findViewById(R.id.tvTimeDisplay3);
        tvTimeDisplay4 = (TextView) findViewById(R.id.tvTimeDisplay4);
        tvStatus1 = (TextView) findViewById(R.id.tvStatus1);
        tvStatus2 = (TextView) findViewById(R.id.tvStatus2);
        tvStatus3 = (TextView) findViewById(R.id.tvStatus3);
        tvStatus4 = (TextView) findViewById(R.id.tvStatus4);

        // Setting button clicked
        btSetting.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openAlarmSettingActivity();
                    }
                });

        // Swipe down to refresh
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchFromServer();
                pullToRefresh.setRefreshing(false);
            }
        });
    }
    private void openAlarmSettingActivity() {
        Intent intent = new Intent(this, AlarmSettingActivity.class);
        startActivity(intent);
    }
    private String getTime(int alarmNum) {
        String time = null;
        // TODO: getting alarm time from server
        return time;
    }
    private String getStatus(int alarmNum) {
        String status = null;
        // TODO: getting alarm status from server
        return status;
    }
    private void fetchFromServer() {
        tvTimeDisplay1.setText(getTime(1));
        tvTimeDisplay2.setText(getTime(2));
        tvTimeDisplay3.setText(getTime(3));
        tvTimeDisplay4.setText(getTime(4));
        tvStatus1.setText(getStatus(1));
        tvStatus2.setText(getStatus(2));
        tvStatus3.setText(getStatus(3));
        tvStatus4.setText(getStatus(4));
    }
}
