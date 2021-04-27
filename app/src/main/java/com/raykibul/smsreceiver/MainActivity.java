package com.raykibul.smsreceiver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MY_SMS_PERMISSION=122;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){
                Toast.makeText(this, "SMS PERMISSION DENIED BY USER", Toast.LENGTH_SHORT).show();
            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS},MY_SMS_PERMISSION);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==MY_SMS_PERMISSION&&resultCode==RESULT_OK){
            Toast.makeText(this, "Thank You for permitting SMS Read Permission!", Toast.LENGTH_SHORT).show();
        }
    }
}