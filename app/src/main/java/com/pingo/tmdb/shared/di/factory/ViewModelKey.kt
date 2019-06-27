package com.pingo.tmdb.shared.di.factory

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-26.
 * ----------------------------------------------
 *
 * Maps all the keys holding the view models.
 * @property value KClass<out ViewModel>
 * @constructor
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
