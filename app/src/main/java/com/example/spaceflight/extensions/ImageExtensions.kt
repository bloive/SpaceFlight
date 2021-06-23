package com.example.spaceflight.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.spaceflight.R

fun ImageView.fromUrl(url: String) {
    Glide.with(this.context).load(url).placeholder(R.drawable.photo_1559638753_d8838a6ac071).into(this)
}