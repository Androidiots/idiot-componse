package com.androidiots.compose.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget

class Tools {
    companion object {

        @JvmStatic
        fun displayImageRound(ctx: Context, img: ImageView, @DrawableRes drawable: Int) {
            try {
                Glide.with(ctx).load(drawable).asBitmap().centerCrop()
                    .into(object : BitmapImageViewTarget(img) {
                        override fun setResource(resource: Bitmap?) {
                            val circularBitmapDrawable: RoundedBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(ctx.resources, resource)
                            circularBitmapDrawable.setCircular(true)
                            img.setImageDrawable(circularBitmapDrawable)
                        }
                    })
            } catch (e: Exception) {
            }
        }
    }
}