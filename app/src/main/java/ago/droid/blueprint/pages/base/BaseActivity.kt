package ago.droid.blueprint.pages.base

import ago.droid.blueprint.MainApplication
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        (application as MainApplication).setCurrentActivity(this)
    }
}