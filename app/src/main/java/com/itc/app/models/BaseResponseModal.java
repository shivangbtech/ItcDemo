package com.itc.app.models;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

public class BaseResponseModal {
    @SerializedName("code")
    private int code;
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String errorText;

    private String localizedMessage;
    private Object rowResponse;
    private String requestedUrl;

    //Local variable
    private String apiType;

    @Nullable
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) { this.errorText = errorText;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public Object getRowResponse() {
        return rowResponse;
    }

    public void setRowResponse(Object rowResponse) {
        this.rowResponse = rowResponse;
    }

    public String getRequestedUrl() {
        return requestedUrl;
    }

    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }
}
