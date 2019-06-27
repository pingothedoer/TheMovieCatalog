package com.pingo.tmdb.shared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.pingo.tmdb.R
import com.pingo.tmdb.shared.ext.args

/**
 * Created By : Muhammad Ali Ansari
 * Dated :  2019-06-27.
 * ---------------------------------------------
 *
 * Dialog to show progress with given title and message
 */

class Progress : DialogFragment() {

    companion object {
        private const val KEY_TITLE = "title"
        private const val KEY_MESSAGE = "message"

        @JvmStatic
        private fun newInstance(title: String, message: String) = Progress().args {
            putString(KEY_TITLE, title)
            putString(KEY_MESSAGE, message)
        } as DialogFragment

        @JvmStatic
        fun show(manager: FragmentManager, title: String, message: String) = newInstance(title, message).run {
            show(manager, null)
            this as Progress
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_progress, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.setCancelable(false)

        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvMessage = view.findViewById<TextView>(R.id.tv_message)

        tvTitle.text = arguments!!.getString(KEY_TITLE)
        tvMessage.text = arguments!!.getString(KEY_MESSAGE)

    }
}