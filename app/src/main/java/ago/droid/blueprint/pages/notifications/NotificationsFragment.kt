package ago.droid.blueprint.pages.notifications

import ago.droid.blueprint.MainApplication
import ago.droid.blueprint.R
import ago.droid.blueprint.adapters.ComponentAdapter
import ago.droid.blueprint.viewmodels.home.HomeViewModel
import ago.droid.blueprint.viewmodels.notifications.NotificationsViewModel
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import javax.inject.Inject


class NotificationsFragment : Fragment() {

    @Inject
    lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity?.application as MainApplication).appComponent.inject(this)
        //notificationsViewModel =
                //ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar : ProgressBar = view.findViewById(R.id.pbComponent)
        val lvComponents: RecyclerView = view.findViewById(R.id.lvComponent)
        lvComponents.layoutManager = LinearLayoutManager(activity?.applicationContext)
        notificationsViewModel.components.observe(viewLifecycleOwner, Observer {
            //textView.text = it
            var adapter = activity?.let { it1 -> ComponentAdapter(it, it1.applicationContext) }
            lvComponents.adapter = adapter

            when(it.size){
                0 -> progressBar.visibility = View.VISIBLE
                else -> progressBar.visibility = View.GONE
            }
        })
    }
}