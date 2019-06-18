package com.itc.app.network;

import com.itc.app.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit mRetrofit;

    private static ApiClient sInstance;

    public Retrofit getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(RetrofitConfig.LOGGER_LEVEL);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(RetrofitConfig.CONNECT_TIMEOUT, RetrofitConfig.TIME_UNIT_TIMEOUT);
        httpClient.writeTimeout(RetrofitConfig.WRITE_TIMEOUT, RetrofitConfig.TIME_UNIT_TIMEOUT);
        httpClient.readTimeout(RetrofitConfig.READ_TIMEOUT, RetrofitConfig.TIME_UNIT_TIMEOUT);
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(logging);
        }

        String baseUrl = RetrofitConfig.BASE_URL;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return mRetrofit;

    }

    private ApiClient() {
        mRetrofit = getClient();
    }

    public static ApiClient getInstance() {
        if (sInstance == null)
            sInstance = new ApiClient();
        return sInstance;
    }
}
