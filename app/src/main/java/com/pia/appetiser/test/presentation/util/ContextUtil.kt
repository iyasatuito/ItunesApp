package com.pia.appetiser.test.presentation.util

import android.content.Context
import android.graphics.Point
import android.view.WindowManager


fun Context.getScreenHeight(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}

fun Context.getScreenWidth(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

fun Context.pixelsToSp(px: Float): Float {
    val scaledDensity = resources.displayMetrics.scaledDensity
    return px / scaledDensity
}
