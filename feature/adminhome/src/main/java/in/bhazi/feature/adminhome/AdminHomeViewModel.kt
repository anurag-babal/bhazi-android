package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.domain.RefreshOrdersUseCase
import `in`.bhazi.core.model.Order
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(
    private val refreshOrderUseCase: RefreshOrdersUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<AdminHomeUiState> =
        MutableStateFlow(AdminHomeUiState(loading = true))
    val adminHomeUiState = _uiState

    init {
        fetchOrders()
    }

    fun onClickMenuItem(
        status: String = _uiState.value.selectedStatus,
        type: String = _uiState.value.selectedType
    ) {
        _uiState.value = _uiState.value.copy(
            loading = true, selectedStatus = status, selectedType = type
        )
        fetchOrders()
    }

    private fun fetchOrders() {
        viewModelScope.launch {
            val orders = refreshOrderUseCase(
                day = DAY.valueOf(_uiState.value.selectedType.uppercase()).description,
                status = _uiState.value.selectedStatus
            ).data!!
            _uiState.value = _uiState.value.copy(loading = false, orders = orders)
        }
    }
}

enum class DAY(val displayName: String, val description: String) {
    TODAY("Today", "price"),
    YESTERDAY("Yesterday", "delivery")
}

data class AdminHomeUiState(
    val loading: Boolean = false,
    val orders: List<Order> = listOf(),
    val statuses: List<String> = listOf("Ordered", "Out For Delivery", "Delivered", "Cancelled"),
    val selectedStatus: String = "Ordered",
    val types: List<String> = DAY.values().map { it.displayName },
    val selectedType: String = "Yesterday"
)