package client.lifcare.github

import com.squareup.moshi.Moshi
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Abhishek on 20.09.2018.
 */


inline fun <reified T> Moshi.fromJson(json: String): T = this.adapter(T::class.java).fromJson(json)

