package com.okawa.blockchain.mkt.utils

import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment

fun Fragment.setStatusBarColor(@ColorInt color: Int) {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
}