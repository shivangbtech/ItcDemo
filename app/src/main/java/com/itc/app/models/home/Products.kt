package com.itc.app.models.home

import com.itc.app.models.BaseResponseModal

data class Products(
    val products: List<Product>, val pageNumber: Int, val pageSize: Int,
    val statusCode: Int
): BaseResponseModal()