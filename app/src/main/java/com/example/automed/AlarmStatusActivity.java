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
    Switch swAlarm1;
    Switch swAlarm2;
    Switch swAlarm3;
    Switch swAlarm4;
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
        swAlarm1 = (Switch) findViewById(R.id.swAlarm1);
        swAlarm2 = (Switch) findViewById(R.id.swAlarm2);
        swAlarm3 = (Switch) findViewById(R.id.swAlarm3);
        swAlarm4 = (Switch) findViewById(R.id.swAlarm4);

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
        // delay handler
        final Handler handler = new Handler();
        // display after delay so every thing is uptodate
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                displayFetchedText();
            }
        }, 3000);

        // change switch texts on toggle
        swAlarm1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO: get time text shows 1 at certain position in the string
            }
        });
        //TODO: repeat above for 3 other alarms

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
                        Toast.makeText(context, builder.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
        return ;
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
        status1="-"+allTime.substring(0,nthIndexOf(allTime,"-", 1));
        hour1=allTime.substring(nthIndexOf(allTime,"-", 1)+1,nthIndexOf(allTime,"-", 2));
        min1=allTime.substring(nthIndexOf(allTime,"-", 2)+1,nthIndexOf(allTime,"-", 3));

        status2=allTime.substring(nthIndexOf(allTime,"-", 3),nthIndexOf(allTime,"-", 4));
        hour2=allTime.substring(nthIndexOf(allTime,"-", 4)+1,nthIndexOf(allTime,"-", 5));
        min2=allTime.substring(nthIndexOf(allTime,"-", 5)+1,nthIndexOf(allTime,"-", 6));

        status3=allTime.substring(nthIndexOf(allTime,"-", 6),nthIndexOf(allTime,"-", 7));
        hour3=allTime.substring(nthIndexOf(allTime,"-", 7)+1,nthIndexOf(allTime,"-", 8));
        min3=allTime.substring(nthIndexOf(allTime,"-", 8)+1,nthIndexOf(allTime,"-", 9));

        status4=allTime.substring(nthIndexOf(allTime,"-", 9),nthIndexOf(allTime,"-", 10));
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
        if (str.equals("-0")){
            statusMsg = "Deactivated";
        }
        if (str.equals("-1")){
            statusMsg = "Pending"; // for tomorrow
        }
        if (str.equals("-2")){
            statusMsg = "Pending"; // for today
        }
        if (str.equals("-3")){
            statusMsg = "Pill Taken";
        }
        if (str.equals("-4")){
            statusMsg = "Pill Not Taken";
        }

        return statusMsg;
    }

    private void displayFetchedText() {
        tvTimeDisplay1.setText(hour1 + ":" + min1);
        tvTimeDisplay2.setText(hour2 + ":" + min2);
        tvTimeDisplay3.setText(hour3 + ":" + min3);
        tvTimeDisplay4.setText(hour4 + ":" + min4);
        /*swAlarm1.setText(status1);
        swAlarm2.setText(status2);
        swAlarm3.setText(status3);
        swAlarm4.setText(status4);*/
        System.out.println(status1.getClass());
        //System.out.println("["+status1+"]");
        String msg1 = determineStatus(status1);
        String msg2 = determineStatus(status2);
        String msg3 = determineStatus(status3);
        String msg4 = determineStatus(status4);
        swAlarm1.setText(msg1);
        swAlarm2.setText(msg2);
        swAlarm3.setText(msg3);
        swAlarm4.setText(msg4);
    }

    private void openAlarmSettingActivity() {
        Intent intent = new Intent(this, AlarmSettingActivity.class);
        startActivity(intent);
    }


}
