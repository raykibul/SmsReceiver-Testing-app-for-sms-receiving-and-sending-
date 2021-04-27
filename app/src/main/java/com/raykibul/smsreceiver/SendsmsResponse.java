package com.raykibul.smsreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SendsmsResponse extends AppCompatActivity {

    TextView numbertext,msgtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms_response);
        numbertext=findViewById(R.id.number);
        msgtext=findViewById(R.id.msg);

        Intent intent=getIntent();
        if (intent.getExtras()!=null) {
            String msg = intent.getExtras().getString("SMS");
            String number = intent.getExtras().getString("NUMBER");

            numbertext.setText("NUMNBER: "+number);
            msgtext.setText("MSG: "+msg);
        }

    }
}