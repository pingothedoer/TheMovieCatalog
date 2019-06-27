package com.pingo.tmdb.shared.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ---------------------------------------------
 *
 * Base Fragment holding [ViewDataBinding] and [ViewModel]
 * @param VB : [ViewDataBinding]
 * @param VM : [ViewModel]
 * @property layout Int
 * @property viewModelFactory Factory
 * @property viewModelObj VM
 * @property binding VB
 * @property viewModelClass KClass<VM>
 * @constructor
 */
abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel>(@LayoutRes private var layout: Int) :
    DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModelObj: VM
    protected lateinit var binding: VB

    abstract val viewModelClass: KClass<VM>
    var progressDialog: Progress? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObj = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass.java)
    }

    fun startProgress(title: String, message: String) {
        if (progressDialog != null) {
            progressDialog?.dismiss()
        }
        progressDialog = Progress.show(fragmentManager!!, title, message)
    }


    fun stopProgress() {
        if (progressDialog != null) {
            // dismiss dialog after at least 1 sec delay
            Handler().postDelayed({ progressDialog?.dismiss() }, 1000L)
        }
    }

}
