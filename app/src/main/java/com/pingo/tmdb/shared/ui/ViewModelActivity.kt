package com.pingo.tmdb.shared.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Base activity for activities containing ViewModels for dependency injection
 */
abstract class ViewModelActivity<VM : ViewModel> : BaseActivity {

    constructor() : super()

    constructor(@LayoutRes layout: Int) : super(layout)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /** override val viewModelCass = MyViewModel::class */
    abstract val viewModelClass: KClass<VM>

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass.java)
    }
}