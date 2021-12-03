package com.example.automed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    // xml objects
    EditText etIP;
    Button btContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // Assigning content to variables
        btContinue = (Button) findViewById(R.id.btContinue);
        etIP = (EditText)findViewById(R.id.etIP);
        // On click actions
        btContinue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openAlarmStatusActivity();
                    }
                });
    }
    private void openAlarmStatusActivity() {
        String IP_address = etIP.getText().toString();
        Intent intent = new Intent(this, AlarmStatusActivity.class);
        startActivity(intent);
        // TODO: send IP_address to server
        // TODO: fetch alarm status from IP address
    }
}