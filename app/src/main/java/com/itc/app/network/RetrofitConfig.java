package com.itc.app.network;


import com.itc.app.constants.AppConstants;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

public class RetrofitConfig {

    public static final String BASE_URL = AppConstants.BASE_URL + "/";

    public static final HttpLoggingInterceptor.Level LOGGER_LEVEL = HttpLoggingInterceptor.Level.BODY; // set your desired log level : NONE, BASIC, HEADERS, BODY
    public static final int READ_TIMEOUT = 30;
    public static final int WRITE_TIMEOUT = 30;
    public static final int CONNECT_TIMEOUT = 30;
    public static final TimeUnit TIME_UNIT_TIMEOUT = TimeUnit.SECONDS;
    public static final String CONVERTER_FACTORY = RetrofitConfig.CONVERTER_FACTORY_JSON;


    // Converter Factory Type
    public static final String CONVERTER_FACTORY_XML = "xml";
    public static final String CONVERTER_FACTORY_JSON = "json";
    public static final String CONVERTER_FACTORY_SCALARS = "scalars";
}
