package com.pingo.tmdb.shared.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pingo.tmdb.app.App
import com.pingo.tmdb.shared.models.ProgressModel
import com.pingo.tmdb.shared.utils.ErrorHandler
import okhttp3.ResponseBody

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

    /**
     * Show/Hide connection error dialog
     */
    val errorMessage = MutableLiveData<String>()


    /**
     * Parse error if needed
     * @param error Throwable?
     */
    protected open fun onBaseError(error: Throwable?) {
        showProgress.postValue(ProgressModel(show = false))
        when (val errorStr = ErrorHandler.getError(context, error)) {
            ErrorHandler.INTERNET_ERROR -> connectionError.postValue(true)
            else -> errorMessage.postValue(errorStr)
        }
    }

    /**
     * Parse error
     * @param error ResponseBody?
     */
    protected open fun onBaseError(error: ResponseBody?) {
        showProgress.postValue(ProgressModel(show = false))
        val errorStr = ErrorHandler.getError(context, error?.string())
        errorMessage.postValue(errorStr)
    }

    /**
     * Get string value from strings.xml file
     * @param strResId Int
     * @return String
     */
    protected fun getString(strResId: Int): String = context.getString(strResId)

}