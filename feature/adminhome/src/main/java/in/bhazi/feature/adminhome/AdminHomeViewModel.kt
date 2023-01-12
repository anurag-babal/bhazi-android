package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.model.Order
import `in`.bhazi.core.model.Product
import `in`.bhazi.core.network.model.OrderDto
import `in`.bhazi.core.network.model.ProductDto
import `in`.bhazi.core.network.retrofit.ProductRemoteDataSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ViewModel() {
    private val _orders: MutableStateFlow<List<Order>> = MutableStateFlow(listOf())
    val orders: StateFlow<List<Order>> = _orders
    val statuses = listOf("Ordered", "Out For Delivery", "Delivered", "Cancelled")
    val types = DAY.values().map { it.displayName }
    var selectedStatus: String = statuses[0]
    var selectedType: String = types[0]

    init {
        fetchOrders()
    }

    fun onClickMenuItem(status: String = selectedStatus, type: String = selectedType) {
        selectedStatus = status
        selectedType = type
        fetchOrders()
    }

    private fun fetchOrders() {
        viewModelScope.launch {
            val remoteOrders = productRemoteDataSource
                .fetchOrdersForAdmin(selectedStatus, DAY.valueOf(selectedType.uppercase()).description)
            remoteOrders.onSuccess {
                _orders.value = it.map { orderDto -> orderDto.toOrder() }
            }
        }
    }

    private fun OrderDto.toOrder(): Order {
        return Order(id = id, status = status, customerName = customerName)
    }
}

enum class DAY(val displayName: String, val description: String) {
    TODAY("Today", "price"),
    YESTERDAY("Yesterday", "delivery")
}