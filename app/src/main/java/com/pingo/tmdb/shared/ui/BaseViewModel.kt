package com.pingo.tmdb.shared.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pingo.tmdb.app.App
import com.pingo.tmdb.shared.models.ProgressModel
import com.pingo.tmdb.shared.utils.ErrorHandler

/**
 * Base View model
 * @property context App
 * @property showProgress MutableLiveData<BannerUIModel>
 * @property connectionError MutableLiveData<Boolean>
 * @constructor
 */
abstract class BaseViewModel(var context: App) : ViewModel() {

    /**
     * controls progress dialog with [ProgressModel]
     */
    val showProgress = MutableLiveData<ProgressModel>()

    /**
     * Show/Hide connection error dialog
     */
    val connectionError = MutableLiveData<Boolean>()


    protected open fun onBaseError(error: Throwable?): String? {
        showProgress.postValue(ProgressModel(show = false))
        val errorStr = ErrorHandler.getError(context, error)
        return if (errorStr == ErrorHandler.INTERNET_ERROR) {
            connectionError.postValue(true)
            null
        } else {
            errorStr
        }
    }

    /**
     * Get string value from strings.xml file
     * @param strResId Int
     * @return String
     */
    protected fun getString(strResId: Int): String = context.getString(strResId)

}