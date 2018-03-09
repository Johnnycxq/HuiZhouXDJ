package com.rehome.huizhouxdj.utils;

import android.content.Context;
import android.util.Log;

import com.rehome.huizhouxdj.contans.Contans;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ruihong on 2017/12/22.
 */

public class RetrofitHttpUtils {

    private static Api api;

    private static Context context;

    private static RetrofitHttpUtils instance = null;

    public static RetrofitHttpUtils getInstance(Context context, String url) {
        if (instance == null) {
            instance = new RetrofitHttpUtils(context, url);
        }
        return instance;
    }

    private RetrofitHttpUtils(Context context, String url) {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = mRetrofit.create(Api.class);
        this.context = context;
    }

    public static Api getApi() {
        return getApi(Contans.IP);
    }


    public static Api getApi_yx() {
        return getApi(Contans.IP);
    }

    public static Api getDownLoadApi() {

        Retrofit mRetrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = mRetrofit.create(Api.class);

        return api;
    }

    public static Api getApi(String url) {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = mRetrofit.create(Api.class);

        return api;
    }


    private static OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(new LoggingInterceptor());

        //设置超时时间
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);

        return httpClientBuilder.build();
    }

    public static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();

            long t1 = System.nanoTime();//请求发起的时间
            Log.d("okhttpmsg", String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();//收到响应的时间

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);

            Log.d("okhttpmsg", String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));

            return response;
        }
    }
}
