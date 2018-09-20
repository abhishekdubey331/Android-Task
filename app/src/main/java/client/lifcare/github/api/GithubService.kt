package client.lifcare.github.api

import client.lifcare.github.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Abhishek on 20.09.2018.
 */

interface GithubService {

    @GET(ApiSettings.GET_USERS_URL)
    fun getUsersObject(@Query(ApiSettings.QUERY) typedData: String): Observable<UserModel>

}