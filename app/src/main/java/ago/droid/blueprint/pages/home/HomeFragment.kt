package ago.droid.blueprint.pages.home

import ago.droid.blueprint.MainApplication
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ago.droid.blueprint.R
import ago.droid.blueprint.adapters.ComponentAdapter
import ago.droid.blueprint.adapters.HomeAdapter
import ago.droid.blueprint.viewmodels.home.HomeViewModel
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import ago.droid.blueprint.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    @Inject lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        (activity?.application as MainApplication).appComponent.inject(this)
        var binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.notifyId = R.id.action_navigation_home_to_navigation_notifications
        binding.dashboardId = R.id.action_navigation_home_to_navigation_dashboard
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
    }

    private fun setupListAdapter() {
        activity?.let {
            val progressBar : ProgressBar = it.findViewById(R.id.progressBar)
            val lvCard: RecyclerView = it.findViewById(R.id.lvCard)
            lvCard.layoutManager = LinearLayoutManager(activity?.applicationContext)
            
            homeViewModel.cards.observe(viewLifecycleOwner, Observer {
                var adapter = activity?.let { it1 -> HomeAdapter(it, it1.applicationContext) }
                lvCard.adapter = adapter
            })
        }
    }

}