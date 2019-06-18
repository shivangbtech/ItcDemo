package com.example.raku.itcapp.views.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.raku.itcapp.components.Event
import com.itc.app.models.BaseResponseModal
import com.itc.app.models.home.Products
import com.itc.app.network.ApiCallback
import com.itc.app.network.DataServiceFactory
import com.itc.app.network.ResponseHandler

class HomeViewModel : ViewModel() {

    internal var mldProducts = MutableLiveData<Products>()
    internal var mldError = MutableLiveData<Event<BaseResponseModal>>()

    internal fun getListRows(pageNumber: Int, pageSize: Int): MutableLiveData<Products> {
        val apiInterface = DataServiceFactory.getInstance().homeApi
        val call = apiInterface.getProducts(pageNumber, pageSize)
        call.enqueue(ResponseHandler<Products>(object : ApiCallback<Products> {
            override fun onSuccess(t: Products?) {
                mldProducts.value = t as Products
            }

            override fun onError(responseModal: BaseResponseModal?) {
                mldError.value = Event(responseModal!!);
            }
        }))

        return mldProducts
    }

}