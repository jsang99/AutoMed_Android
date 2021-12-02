package com.example.automed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {
    // Variables
    EditText etIP;
    Button btContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btContinue = (Button) findViewById(R.id.btContinue);
        etIP = (EditText)findViewById()
        btContinue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openNewActivity();
                    }
                });
    }

    private void openNewActivity() {
        Intent intent = new Intent(this, AlarmStatusViewActivity.class);
        startActivity(intent);
    }
}