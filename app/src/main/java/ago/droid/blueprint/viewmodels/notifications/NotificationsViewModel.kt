package ago.droid.blueprint.viewmodels.notifications

import ago.droid.blueprint.data.models.ComponentModel
import ago.droid.blueprint.domain.entities.Component
import ago.droid.blueprint.domain.usecases.FetchComponentsUseCase
import ago.droid.blueprint.domain.usecases.FetchDCardsUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsViewModel @Inject constructor(
    private val fetchComponentsUseCase: FetchComponentsUseCase
) : ViewModel() {

    private var _components = MutableLiveData<List<Component>>().apply {
        value = ArrayList<Component>()
    }
    val components: LiveData<List<Component>> = _components

    init {
        loadData()
    }
    private fun loadData()  {
        viewModelScope.launch {
            val result = fetchComponentsUseCase(Unit)
            _components.value = result
        }

    }
}