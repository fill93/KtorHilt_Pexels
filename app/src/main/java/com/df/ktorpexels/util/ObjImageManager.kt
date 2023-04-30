package com.df.ktorpexels.util

import android.widget.ImageView
import com.df.ktorpexels.R
import com.squareup.picasso.Picasso

object ObjImageManager {

    fun load(imageView: ImageView, imageUrl: String?) {

        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}
