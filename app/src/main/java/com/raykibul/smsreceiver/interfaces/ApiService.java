package com.raykibul.smsreceiver.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/api/paymentsms.php")
    Call<ResponseBody> sendSmsResponse(@Field("msg") String messageBody);

    @FormUrlEncoded
    @POST("/api/reload.php")
    Call<ResponseBody> reload(@Field("user") String username,
                          @Field("key") String password);

    @FormUrlEncoded
    @POST("/api/flexiload.php")
    Call<ResponseBody> Flexiload(@Field("user") String username,
                                 @Field("key") String password,
                                 @Field("number") String number,
                                 @Field("amount") String amount,
                                 @Field("type") String type,
                                 @Field("opcode") String opcode);
}
