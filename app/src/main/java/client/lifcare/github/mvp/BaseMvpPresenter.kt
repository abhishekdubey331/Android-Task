package client.lifcare.github.mvp

/**
 * Created by Abhishek on 20.09.2018.
 */


interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}