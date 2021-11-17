package ago.droid.blueprint.pages.dashboard

import ago.droid.blueprint.R
import ago.droid.blueprint.viewmodels.dashboard.DashboardViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        val ivFirst: ImageView = view.findViewById(R.id.ivFirst)
        Glide.with(this)
            .load("https://bosat.vn/gallery/rong-3.jpg")
            .transition(withCrossFade(factory))
            .into(ivFirst)

        val ivSecond: ImageView = view.findViewById(R.id.ivSecond)
        Glide.with(this)
            .load("https://bosat.vn/gallery/rong-3.jpg")
            .transition(withCrossFade(factory))
            .into(ivSecond)
    }
}