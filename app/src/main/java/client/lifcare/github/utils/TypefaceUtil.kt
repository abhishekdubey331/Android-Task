package app.appgully.lifehacks.utils

import android.graphics.Typeface
import com.thefinestartist.Base


object TypefaceUtil {

    val getHammerSmith: Typeface
        get() {
            val font_name = "HammersmithOne-Regular.otf"
            return Typeface.createFromAsset(Base.getAssets(), "fonts/$font_name")
        }


}