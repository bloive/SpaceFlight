package com.example.spaceflight.extensions

import android.view.View
import android.widget.Button
import androidx.core.content.res.ResourcesCompat.getDrawable
import com.example.spaceflight.R

fun View.expand(btn: Button) {
//    TransitionManager.beginDelayedTransition(this as ViewGroup?, AutoTransition())
    visibility = View.VISIBLE
    btn.background = getDrawable(resources, R.drawable.ic_baseline_keyboard_arrow_down_24, null)
}

fun View.collapse(btn: Button) {
//    TransitionManager.beginDelayedTransition(this as ViewGroup?, AutoTransition())
    visibility = View.GONE
    btn.background = getDrawable(resources, R.drawable.ic_baseline_keyboard_arrow_up_24, null)
}