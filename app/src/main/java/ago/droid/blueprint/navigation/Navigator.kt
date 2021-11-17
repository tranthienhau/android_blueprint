package ago.droid.blueprint.navigation

import ago.droid.blueprint.MainApplication
import ago.droid.blueprint.data.remote.WebApi
import androidx.navigation.findNavController
import javax.inject.Inject
import javax.inject.Singleton


interface Navigator {
    fun navigateByNavController(navId: Int, fragmentId:Int)
}

class NavigatorImpl @Inject constructor(private val application: MainApplication) :Navigator {
    override fun navigateByNavController(navId: Int, fragmentId: Int) {
        application.getCurrentActivity()?.let {
            it.findNavController(navId).navigate(fragmentId)
        }
    }

}