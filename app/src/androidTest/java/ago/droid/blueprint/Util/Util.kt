package ago.droid.blueprint.Util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

object Util {
    @JvmStatic
    fun dpiToPx(c: Context, dpi: Float): Int {
        val metrics = c.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, metrics).toInt()
    }
}