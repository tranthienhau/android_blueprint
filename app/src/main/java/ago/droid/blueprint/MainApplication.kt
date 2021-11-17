package ago.droid.blueprint

import ago.droid.blueprint.core.di.AppModule
import ago.droid.blueprint.core.di.ApplicationComponent
import ago.droid.blueprint.core.di.DaggerApplicationComponent
import android.app.Activity
import android.app.Application

/// Custom application
class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private var mCurrentActivity:Activity? = null;

    fun setCurrentActivity(activity: Activity){
        mCurrentActivity = activity
    }

    fun getCurrentActivity():Activity ? = mCurrentActivity

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): ApplicationComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerApplicationComponent.create()
    }

}