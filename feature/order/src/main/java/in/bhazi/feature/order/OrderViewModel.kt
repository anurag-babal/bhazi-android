package `in`.bhazi.feature.order

import `in`.bhazi.core.domain.GetOrderDetailUseCase
import `in`.bhazi.core.domain.UpdateOrderStatusUseCase
import `in`.bhazi.core.model.Order
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getOrderDetailUseCase: GetOrderDetailUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val orderId: Long = checkNotNull(savedStateHandle.get<Long>(orderIdArg))
    private val _uiState: MutableStateFlow<OrderUiState> =
        MutableStateFlow(OrderUiState(loading = true))
    val uiState: StateFlow<OrderUiState> = _uiState

    init {
        getOrderDetail()
    }

    private fun getOrderDetail() {
        viewModelScope.launch {
            val order = getOrderDetailUseCase(orderId)
            _uiState.value = _uiState.value.copy(loading = false, order = order)
        }
    }

    fun onClickStatus(status: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            val order = updateOrderStatusUseCase(orderId = orderId, status = status)
            _uiState.value = _uiState.value.copy(order = order, loading = false)
        }
    }
}

data class OrderUiState(
    val loading: Boolean = false,
    val enabled: Boolean = true,
    val order: Order? = null
)