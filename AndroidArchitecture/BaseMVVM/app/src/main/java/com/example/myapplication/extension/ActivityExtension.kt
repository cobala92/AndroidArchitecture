package com.example.myapplication.extension

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.ui.base.BaseVMFactory

/**
 * Created by PhuongDang on 5/27/20
 */
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null) {
        ViewModelProviders.of(this)[T::class.java]
    } else {
        ViewModelProviders.of(this, BaseVMFactory(creator))[T::class.java]
    }
}
