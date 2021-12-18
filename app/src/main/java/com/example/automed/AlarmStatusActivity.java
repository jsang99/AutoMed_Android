package com.example.automed;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AlarmStatusActivity extends AppCompatActivity{
    // xml objects
    Button btSetting;
    SwipeRefreshLayout pullToRefresh;
    TextView tvTimeDisplay1;
    TextView tvTimeDisplay2;
    TextView tvTimeDisplay3;
    TextView tvTimeDisplay4;
    TextView tvAlarm1;
    TextView tvAlarm2;
    TextView tvAlarm3;
    TextView tvAlarm4;
    public static String allTime;
    public static String status1;
    public static String hour1;
    public static String min1;
    public static String status2;
    public static String hour2;
    public static String min2;
    public static String status3;
    public static String hour3;
    public static String min3;
    public static String status4;
    public static String hour4;
    public static String min4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmstatus);
        // get time when activity created
        getAllTime();

        // initializing variables
        btSetting = (Button) findViewById(R.id.btSetting);
        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        tvTimeDisplay1 = (TextView) findViewById(R.id.tvTimeDisplay1);
        tvTimeDisplay2 = (TextView) findViewById(R.id.tvTimeDisplay2);
        tvTimeDisplay3 = (TextView) findViewById(R.id.tvTimeDisplay3);
        tvTimeDisplay4 = (TextView) findViewById(R.id.tvTimeDisplay4);
        tvAlarm1 = (TextView) findViewById(R.id.tvAlarm1);
        tvAlarm2 = (TextView) findViewById(R.id.tvAlarm2);
        tvAlarm3 = (TextView) findViewById(R.id.tvAlarm3);
        tvAlarm4 = (TextView) findViewById(R.id.tvAlarm4);

        // delay handler
        final Handler handler = new Handler();
        // display after delay so every thing is uptodate
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                getAllTime();

                displayFetchedText();
            }
        }, 3000);

        // "Setting" button clicked
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
                getAllTime();
                displayFetchedText();
                pullToRefresh.setRefreshing(false);
            }
        });
    }
    // get time
    private void getAllTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    //String url="https://www.google.com";
                    //String url="http://10.103.215.78/get_time";
                    String url;
                    url = "http://" + WelcomeActivity.IP_address + "/get_time";
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
                        allTime = builder.toString();
                        parseAllTime();
                        Context context = getApplicationContext();
                        Toast.makeText(context, "Data Fetched", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }

    public static int nthIndexOf(String source, String sought, int n) {
        int index = source.indexOf(sought);
        for (int i = 1; i < n; i++) {
            index = source.indexOf(sought, index + 1);
        }
        return index;
    }

    private String leadingZero(String str) {
        String temp;
        if (str.length()==1) {
            temp = "0"+str;
        } else {
            temp = str;
        };
        return temp;
    }

    private void parseAllTime() {
        status1=allTime.substring(0,nthIndexOf(allTime,"-", 1));
        hour1=allTime.substring(nthIndexOf(allTime,"-", 1)+1,nthIndexOf(allTime,"-", 2));
        min1=allTime.substring(nthIndexOf(allTime,"-", 2)+1,nthIndexOf(allTime,"-", 3));

        status2=allTime.substring(nthIndexOf(allTime,"-", 3)+1,nthIndexOf(allTime,"-", 4));
        hour2=allTime.substring(nthIndexOf(allTime,"-", 4)+1,nthIndexOf(allTime,"-", 5));
        min2=allTime.substring(nthIndexOf(allTime,"-", 5)+1,nthIndexOf(allTime,"-", 6));

        status3=allTime.substring(nthIndexOf(allTime,"-", 6)+1,nthIndexOf(allTime,"-", 7));
        hour3=allTime.substring(nthIndexOf(allTime,"-", 7)+1,nthIndexOf(allTime,"-", 8));
        min3=allTime.substring(nthIndexOf(allTime,"-", 8)+1,nthIndexOf(allTime,"-", 9));

        status4=allTime.substring(nthIndexOf(allTime,"-", 9)+1,nthIndexOf(allTime,"-", 10));
        hour4=allTime.substring(nthIndexOf(allTime,"-", 10)+1,nthIndexOf(allTime,"-", 11));
        min4=allTime.substring(nthIndexOf(allTime,"-", 11)+1);

        hour1=leadingZero(hour1);
        min1=leadingZero(min1);
        hour2=leadingZero(hour2);
        min2=leadingZero(min2);
        hour3=leadingZero(hour3);
        min3=leadingZero(min3);
        hour4=leadingZero(hour4);
        min4=leadingZero(min4);
    }

    private String determineStatus(String str){

        String statusMsg = "error";
        if (str.equals("0")){
            statusMsg = "Deactivated";
        }
        if (str.equals("1")){
            statusMsg = "Pending"; // for tomorrow
        }
        if (str.equals("2")){
            statusMsg = "Pending"; // for today
        }
        if (str.equals("3")){
            statusMsg = "Pill Taken";
        }
        if (str.equals("4")){
            statusMsg = "Pill Not Taken";
        }
        return statusMsg;
    }

    private void displayFetchedText() {
        tvTimeDisplay1.setText(hour1 + ":" + min1);
        tvTimeDisplay2.setText(hour2 + ":" + min2);
        tvTimeDisplay3.setText(hour3 + ":" + min3);
        tvTimeDisplay4.setText(hour4 + ":" + min4);
        String msg1 = determineStatus(status1);
        String msg2 = determineStatus(status2);
        String msg3 = determineStatus(status3);
        String msg4 = determineStatus(status4);
        tvAlarm1.setText(msg1);
        tvAlarm2.setText(msg2);
        tvAlarm3.setText(msg3);
        tvAlarm4.setText(msg4);
    }

    private void openAlarmSettingActivity() {
        Intent intent = new Intent(this, AlarmSettingActivity.class);
        startActivity(intent);
    }


}
