package client.lifcare.github.flow.repository_list

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import client.lifcare.github.R
import client.lifcare.github.flow.adapters.UserListAdapter
import client.lifcare.github.model.Item
import client.lifcare.github.model.SearchSuggestions
import client.lifcare.github.mvp.BaseMvpActivity
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import kotlinx.android.synthetic.main.activity_organization.*


/**
 * Created by Abhishek on 20.09.2018.
 */

class RepositoriesListActivity : BaseMvpActivity<RepositoriesContract.View,
        RepositoriesPresenter>(),
        RepositoriesContract.View {


    var handler = Handler()
    var delay: Long = 500
    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization)
        setUpSearch()
    }

    override fun showUserNameSuggestions(suggestions: MutableList<SearchSuggestions>) {
        mSearchView.swapSuggestions(suggestions)
        mSearchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSearchAction(currentQuery: String?) {
                mPresenter.loadUsers(currentQuery!!, true)
            }

            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
                searchSuggestion as SearchSuggestions
                mPresenter.loadUsers(searchSuggestion.suggestion, true)
                mSearchView.clearSuggestions()
                mSearchView.clearSearchFocus()
                mSearchView.setSearchText(searchSuggestion.suggestion)
            }
        })

    }

    override fun showUserData(user: MutableList<Item>) {
        val bannerAdapter = UserListAdapter(user, this) {

        }
        userListRecycler.layoutManager = LinearLayoutManager(this)
        userListRecycler.adapter = bannerAdapter
    }


    override fun showError(error: String?) {
        super.showError(error)
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun setUpSearch() {

        mSearchView.setOnQueryChangeListener { oldQuery, newQuery ->
            if (newQuery.length < 3)
                mSearchView.clearSuggestions()
            else {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    mPresenter.loadUsers(newQuery, false)
                }, delay)
            }
        }
    }
}

