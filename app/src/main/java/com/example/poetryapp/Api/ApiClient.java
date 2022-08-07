package com.example.poetryapp.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit RETROFIT = null;

    public static Retrofit getclient(){

        if(RETROFIT == null){
            // ab hum object OKHttpClient ka :- kyuki yhi Request ko generate krega :-
            // and client ko build krega.
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            // create object of Gson :-
            Gson gson = new GsonBuilder().create();

            RETROFIT = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.41/API/Poetry/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        
        return RETROFIT;
    }
}
