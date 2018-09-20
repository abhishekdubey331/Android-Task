package client.lifcare.github.flow.repository_list

import client.lifcare.github.api.GeneralErrorHandler
import client.lifcare.github.manager.ApiManager
import client.lifcare.github.model.SearchSuggestions
import client.lifcare.github.mvp.BaseMvpPresenterImpl
import rx.Observable
import rx.functions.Action1

/**
 * Created by Abhishek on 20.09.2018.
 */


class RepositoriesPresenter : BaseMvpPresenterImpl<RepositoriesContract.View>(), RepositoriesContract.Presenter {


    override fun loadUsers(typedData: String, showUser: Boolean) {
        ApiManager.loadUsers(typedData)
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe(Action1 { usersList ->
                    if (showUser)
                        mView?.showUserData(usersList.items)
                    else {
                        val array: MutableList<SearchSuggestions> = ArrayList()
                        Observable.just(usersList.items)
                                .doOnCompleted {
                                    mView?.showUserNameSuggestions(array)
                                }
                                .subscribe { item ->
                                    item.forEach {
                                        array.add(SearchSuggestions(it.login))
                                    }
                                }
                    }
                },
                        GeneralErrorHandler(mView, true, { throwable, errorBody, isNetwork -> mView?.showError(throwable.message) }))

    }
}