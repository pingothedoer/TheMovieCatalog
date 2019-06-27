package com.pingo.tmdb.shared.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pingo.tmdb.shared.di.scope.AppScope
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * @property creators MutableMap<Class<out ViewModel>, [@kotlin.jvm.JvmSuppressWildcards] Provider<ViewModel>>
 * @constructor
 */
@AppScope
class ViewModelFactory @Inject constructor(
    private val creators: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}