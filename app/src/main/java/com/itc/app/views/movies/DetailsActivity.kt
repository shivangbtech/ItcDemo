package com.itc.app.views.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.itc.app.R
import com.itc.app.constants.AppConstants
import com.itc.app.helper.GlideHelper
import com.itc.app.models.home.Product
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var mProduct: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        mProduct = intent.getParcelableExtra(AppConstants.PARAM_PRODUCT)
        handleUI()
    }


    /**
     * Method call to handle UI
     */
    private fun handleUI() {
        tv_price.text = "Price: " + mProduct.price
        tv_rating.text = "Rating: " + mProduct.reviewRating.toString()
        tv_review_count.text = "Review Count: " + mProduct.reviewCount.toString()
        tv_stock.text = "Stock: " + if(mProduct.inStock) "In Stock" else "Out Of Stock"
        tv_title_details.text = mProduct.productName
        tv_desc_details.text = mProduct.longDescription
        GlideHelper.instance.loadImage(iv_top, AppConstants.BASE_URL_IMAGE + mProduct.productImage)
    }

}
