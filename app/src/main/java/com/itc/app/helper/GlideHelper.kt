package com.itc.app.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itc.app.R

class GlideHelper private constructor() {


    private object Holder {
        val INSTANCE = GlideHelper()
    }

    companion object {
        val instance: GlideHelper by lazy { GlideHelper.Holder.INSTANCE }
    }


    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
            )
            .load(url)
            //.transition(DrawableTransitionOptions.withCrossFade(1000))//in milliseconds
            //                .thumbnail(0.1f)
            //.apply(RequestOptions.circleCropTransform())
            //.apply(new RequestOptions().transform(new RoundedCorners(50)))
            .into(imageView)
    }
}

