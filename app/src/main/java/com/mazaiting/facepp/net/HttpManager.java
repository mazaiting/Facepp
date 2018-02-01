package com.mazaiting.facepp.net;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.mazaiting.facepp.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author mazaiting
 * @date 2018/1/30
 */

public class HttpManager {

    private static HttpManager sManager = null;
    private HttpManager() {}

    /**
     * 单例
     */
    public static HttpManager getInstance(){
        if (null == sManager) {
            synchronized (HttpManager.class) {
                if (null == sManager) {
                    sManager = new HttpManager();
                }
            }
        }
        return sManager;
    }


    private OkHttpClient getOkHttp() {
        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                .addInterceptor(getHttpLoggingInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return okHttpClient;
    }

    /**
     * 日志输出
     * 自行判定是否添加
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor(){
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("HttpManager","====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    public Retrofit getRetrofit() {
        Retrofit.Builder retrofit =
                new Retrofit.Builder()
                .client(getOkHttp())
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return retrofit.build();
    }


}


















