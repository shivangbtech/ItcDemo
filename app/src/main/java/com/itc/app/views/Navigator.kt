package com.itc.app.views

import android.content.Intent
import com.itc.app.base.BaseActivity
import com.itc.app.constants.AppConstants
import com.itc.app.models.home.Product
import com.itc.app.views.home.DetailsActivity


class Navigator private constructor() {
    init {
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = Navigator()
    }

    companion object {
        val instance: Navigator by lazy { Holder.INSTANCE }
    }

    fun navigateToDetails(activity: BaseActivity, product: Product) {
        val intent: Intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra(AppConstants.PARAM_PRODUCT, product)
        activity.startActivity(intent)
    }
}