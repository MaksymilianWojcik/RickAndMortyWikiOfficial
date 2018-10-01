package com.example.mwojcik.rickandmorty_wiki.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit instance;
    private static final String BASE_URL = "https://rickandmortyapi.com/";
    private static final int HTTP_READ_TIMEOUT = 10000;
    private static final int HTTP_CONNECT_TIMEOUT = 6000;

    private static Retrofit getInstance(){
        if(instance == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return instance;
    }


    public static <S> S createService(Class<S> sClass){
        return getInstance().create(sClass);
    }
}
