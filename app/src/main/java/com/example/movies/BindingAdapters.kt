package com.example.movies

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.makeramen.roundedimageview.RoundedImageView


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url)
            .apply(RequestOptions.bitmapTransform( RoundedCorners(2)))
            .into(this)
}

@BindingAdapter("roundedImageUrl")
fun RoundedImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url)
            .apply(RequestOptions.bitmapTransform( RoundedCorners(14)))
            .override(350, 200)
            .into(this)
}

