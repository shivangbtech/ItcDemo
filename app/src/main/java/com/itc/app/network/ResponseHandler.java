package com.itc.app.network;

import com.itc.app.models.BaseResponseModal;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

public class ResponseHandler<T extends BaseResponseModal> implements retrofit2.Callback<T> {

    private ApiCallback mApiCallback;

    public ResponseHandler(ApiCallback apiCallback) {
        mApiCallback = apiCallback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && mApiCallback != null/* && response.body().getErrorCode() != null*/) {
            switch (response.code()) {
                case NetworkConstant.RESPONSE_CODE_SUCCESS: {
                    if (response.body() instanceof BaseResponseModal) {
                        BaseResponseModal model =  response.body();
                        int code = model.getCode();
                        model.setRowResponse(response.raw());
                        if (code == 0) {
                            mApiCallback.onSuccess(model);
                        } else {
                            mApiCallback.onError(model);
                        }
                    }

                    break;
                }
            }
        } else {
            /**
             * Here to handle error on the bases of code
             */
            switch (response.code()) {
                case NetworkConstant.RESPONSE_CODE_NOT_FOUND:
                case NetworkConstant.RESPONSE_CODE_UN_PROCESSABLE_ENTITY:
                case NetworkConstant.RESPONSE_CODE_LARGE_FILE:
                case NetworkConstant.RESPONSE_CODE_UNAUTHORIZED:
                     handleError(response);
                    break;
                default:
                    handleError(response);
            }
        }
    }

    private void handleError(Response<T> response) {
        BaseResponseModal baseResponseModal = new BaseResponseModal();
        try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            baseResponseModal.setErrorCode(response.code());
            baseResponseModal.setErrorText(jObjError.getString("message"));
            baseResponseModal.setRequestedUrl(response.raw().request().url().toString());
            mApiCallback.onError(baseResponseModal);
        } catch (Exception e) {
            baseResponseModal.setErrorCode(response.code());
            baseResponseModal.setErrorText("Oops! An error has occurred on our server. Please check internet connection and try to playback again!");
            baseResponseModal.setRequestedUrl(response.raw().request().url().toString());
            mApiCallback.onError(baseResponseModal);
//            Logger.e("network error parse error",e.toString());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if(call.isCanceled()){
            return;
        }
        String errorMessage = throwable.toString();
//        Logger.e("ERROR", errorMessage);
        if (throwable instanceof UnknownHostException
                || throwable instanceof ConnectException
                || throwable instanceof IOException) {
            BaseResponseModal baseResponseModal = new BaseResponseModal();
            baseResponseModal.setErrorCode(NetworkConstant.RESPONSE_CODE_NETWORK_ERROR);
            baseResponseModal.setLocalizedMessage(throwable.getLocalizedMessage());
            baseResponseModal.setErrorText(throwable.getMessage());
            mApiCallback.onError(baseResponseModal);
//            mApiCallback.onNetworkErrorCallback(NetworkConstant.ERROR_MESSAGE_NETWORK);
        } else {
            BaseResponseModal baseResponseModal = new BaseResponseModal();
            baseResponseModal.setErrorCode(NetworkConstant.RESPONSE_CODE_SERVER_ERROR);
            baseResponseModal.setLocalizedMessage(throwable.getLocalizedMessage());
            baseResponseModal.setErrorText(throwable.getMessage());
            mApiCallback.onError(baseResponseModal);
        }
    }

}
