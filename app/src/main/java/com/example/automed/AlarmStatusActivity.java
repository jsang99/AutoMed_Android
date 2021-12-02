package com.example.automed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.SwipeRefreshLayout;

public class AlarmStatusActivity extends AppCompatActivity{
    // Variables
    Button btSetting;
    SwipeRefreshLayout pullToRefresh;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmstatus);
        // Assigning content to variables
        btSetting = (Button) findViewById(R.id.btSetting);
        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        // On click actions
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
                // TODO: fetch alarm time and status again
                pullToRefresh.setRefreshing(false);
            }
        });

    }
    private void openAlarmSettingActivity() {
        Intent intent = new Intent(this, AlarmSettingActivity.class);
        startActivity(intent);
    }
}
