package com.example.myapplication.utils.networking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.reactivex.schedulers.Schedulers;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "https://evaluate-expression.p.rapidapi.com/";
    private static Retrofit retrofit;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request newRequest = originalRequest.newBuilder()
                            .addHeader("X-RapidAPI-Key", "0b6d83b662msh80b708fb8778337p1e293bjsn59e4a7c5a828")
                            .addHeader("X-RapidAPI-Host", "evaluate-expression.p.rapidapi.com")
                            .build();
                    return chain.proceed(newRequest);
                }
            });

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface() {
        return getRetrofitInstance().create(ApiInterface.class);
    }
}
