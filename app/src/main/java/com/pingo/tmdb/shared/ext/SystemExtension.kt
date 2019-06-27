@file:Suppress("NOTHING_TO_INLINE")

package com.pingo.tmdb.shared.ext

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Extension methods
 */


/**
 * Make translucent screen
 * @receiver Activity
 */
inline fun Activity.setFullscreen() = with(window) {
    statusBarColor = Color.TRANSPARENT
    addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}


/**
 * Set toolbar in fragment
 * @receiver Activity
 * @param toolbar Toolbar?
 */
inline fun Activity.setToolbar(toolbar: Toolbar?, showTitle: Boolean = false) {
    (this as AppCompatActivity).setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(showTitle)
}

/**
 * Allows calls like
 *
 * `supportFragmentManager.inTransaction { add(...) }`
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

/**
 * Put extras in bundle arguments
 * @receiver Fragment
 * @param bundle [@kotlin.ExtensionFunctionType] Function1<Bundle, Unit>
 * @return Fragment
 */
inline fun Fragment.args(bundle: Bundle.() -> Unit) = apply {
    arguments = Bundle().apply(bundle)
}

