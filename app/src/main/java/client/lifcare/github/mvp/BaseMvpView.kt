package client.lifcare.github.mvp

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by Abhishek on 20.09.2018.
 */


interface BaseMvpView {

    fun getContext(): Context

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)

}
