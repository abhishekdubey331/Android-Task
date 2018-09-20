package client.lifcare.github.manager

import client.lifcare.github.BuildConfig
import client.lifcare.github.api.GithubService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by Abhishek on 20.09.2018.
 */

object ApiManager {

    private const val SERVER: String = "https://api.github.com/"

    private lateinit var mGithubService: GithubService


    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            networkInterceptors().add(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()
                chain.proceed(request)
            })
            if (BuildConfig.DEBUG)
                addInterceptor(interceptor)
        }

        return Retrofit.Builder().baseUrl(SERVER)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(createMoshiConverter())
                .client(client.build())
                .build()
    }

    private fun createMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

    private fun initServices(retrofit: Retrofit) {
        mGithubService = retrofit.create(GithubService::class.java)
    }

    fun loadUsers(typedData: String) =
            mGithubService.getUsersObject(typedData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())!!


}