package com.itc.app.network;


import com.itc.app.network.apiInterface.IHomeApi;
import retrofit2.Retrofit;

public class DataServiceFactory {

    private static DataServiceFactory sDataService;
    private Retrofit mRestClient;

    private DataServiceFactory(Retrofit restClient) {
        mRestClient = restClient;
    }

    public static DataServiceFactory getInstance() {
        if (sDataService == null) {
            sDataService = new DataServiceFactory(ApiClient.getInstance().getClient());
        }
        return sDataService;
    }

    public IHomeApi getHomeApi() {
        return mRestClient.create(IHomeApi.class);
    }
}


