package com.itc.app.network.apiInterface

import com.itc.app.models.home.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IHomeApi {

    /**
     * Interface Method will return the products call
     */
    @GET("walmartproducts/{pageNumber}/{pageSize}")
    fun getProducts(
        @Path("pageNumber") pageNumber: Int,
        @Path("pageSize") pageSize: Int
    ): Call<Products>

}