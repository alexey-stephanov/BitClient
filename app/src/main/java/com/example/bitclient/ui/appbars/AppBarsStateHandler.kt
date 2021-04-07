package com.example.bitclient.ui.appbars

import androidx.fragment.app.Fragment
import timber.log.Timber

interface AppBarsStateHandler {
    fun show()
}

fun Fragment.getAppBarsStateHandler(): AppBarsStateHandler? {
    var currentParentFragment = parentFragment
    while (currentParentFragment != null) {
        if (currentParentFragment is AppBarsStateHandler) {
            return currentParentFragment
        } else {
            currentParentFragment = currentParentFragment.parentFragment
        }
    }
    return activity as? AppBarsStateHandler
}