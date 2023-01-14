package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.domain.GetOrderDetailUseCase
import `in`.bhazi.core.model.Order
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getOrderDetailUseCase: GetOrderDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val orderId: Long = checkNotNull(savedStateHandle.get<Long>(orderIdArg))

    private val _order: MutableStateFlow<Order?> = MutableStateFlow(null)
    val order: StateFlow<Order?> = _order

    init {
        getOrderDetail()
    }

    private fun getOrderDetail() {
        viewModelScope.launch {
            _order.value = getOrderDetailUseCase(orderId)
        }
    }
}