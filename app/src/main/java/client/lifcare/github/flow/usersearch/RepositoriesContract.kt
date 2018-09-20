package client.lifcare.github.flow.repository_list

import client.lifcare.github.model.Item
import client.lifcare.github.model.SearchSuggestions
import client.lifcare.github.mvp.BaseMvpPresenter
import client.lifcare.github.mvp.BaseMvpView

/**
 * Created by Abhishek on 20.09.2018.
 */


object RepositoriesContract {

    interface View : BaseMvpView {
        fun showUserNameSuggestions(suggestions: MutableList<SearchSuggestions>)

        fun showUserData(user: MutableList<Item>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadUsers(typedData: String, showUser: Boolean)
    }


}
