@file:JvmName("ActivityUtil")
package com.pia.appetiser.test.presentation.common.ext

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

import com.pia.appetiser.test.R

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T {
    return ViewModelProviders.of(this)[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}


inline fun <reified T : ViewModel> FragmentActivity.withViewModel(
    viewModelFactory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = getViewModel<T>(viewModelFactory)
    vm.body()
    return vm
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(
    liveData: L,
    body: (T?) -> Unit
) {
    liveData.observe(this, Observer(body))
}


@JvmOverloads
fun Activity.transitionLeftToRight(
    intent: Intent? = null,
    requestCode: Int? = null
) {
    if (intent != null) {
        if (requestCode != null) {
            startActivityForResult(intent, requestCode)
        } else {
            startActivity(intent)
        }
    }
    overridePendingTransition(R.anim.slide_in_right_lib, R.anim.slide_out_right_lib)
}

@JvmOverloads
fun Activity.transitionRightToLeft(
    intent: Intent? = null
) {
    if (intent != null) {
        startActivity(intent)
    }
    overridePendingTransition(R.anim.slide_in_left_lib, R.anim.slide_out_left_lib)
}

@JvmOverloads
fun Activity.transitionUpToDown(
    intent: Intent? = null,
    requestCode: Int? = null
) {
    if (intent != null) {
        if (requestCode != null) {
            startActivityForResult(intent, requestCode)
        } else {
            startActivity(intent)
        }
    }
    overridePendingTransition(R.anim.slide_in_down_lib, R.anim.slide_out_down_lib)
}

@JvmOverloads
fun Activity.transitionDownToUp(
    intent: Intent? = null,
    requestCode: Int? = null
) {
    if (intent != null) {
        if (requestCode != null) {
            startActivityForResult(intent, requestCode)
        } else {
            startActivity(intent)
        }
    }
    overridePendingTransition(R.anim.slide_in_up_lib, R.anim.slide_out_up_lib)
}