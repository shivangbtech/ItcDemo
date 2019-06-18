package com.itc.app.network;


import com.itc.app.models.BaseResponseModal;

public interface ApiCallback<T> {

    void onSuccess(T t);

    void onError(BaseResponseModal responseModal);
}
