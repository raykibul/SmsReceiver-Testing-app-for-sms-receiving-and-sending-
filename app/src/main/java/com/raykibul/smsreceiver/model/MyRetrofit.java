package com.raykibul.smsreceiver.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raykibul.smsreceiver.interfaces.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    private static Retrofit instance;
    private  static  ApiService apiInstance;
     public static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if(instance == null)
            instance= new Retrofit.Builder()
                    .baseUrl("https://sanjidatelecombd.com")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        return instance;
    }

    public static ApiService getApiService(){
        if (apiInstance==null)
        {
            apiInstance=getInstance().create(ApiService.class);
        }
        return  apiInstance;
    }


}
