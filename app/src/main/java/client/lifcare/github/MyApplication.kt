package client.lifcare.github

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.thefinestartist.Base


class MyCustomApplication : Application() {


    override
    fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        Base.initialize(this)
    }

}