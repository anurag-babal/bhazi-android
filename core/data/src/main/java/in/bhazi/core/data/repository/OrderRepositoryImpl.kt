package `in`.bhazi.core.data.repository

import `in`.bhazi.core.data.model.toOrder
import `in`.bhazi.core.data.model.toOrderEntity
import `in`.bhazi.core.model.Order
import `in`.bhazi.core.network.retrofit.OrdersNetworkDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val ordersNetworkDataSource: OrdersNetworkDataSource
) : OrderRepository {
    private val _orders: MutableStateFlow<List<Order>> = MutableStateFlow(listOf())

    override suspend fun refreshOrders(day: String, status: String, page: Int, size: Int) {
        val remoteOrders = ordersNetworkDataSource
            .fetchOrdersForAdmin(status, day, page, size)
        _orders.value = remoteOrders.map { it.toOrderEntity().toOrder() }
    }

    override fun getOrdersFlow(): Flow<List<Order>> {
        return _orders
    }

    override suspend fun getOrderDetail(orderId: Long): Order {
        return _orders.value.find { order -> order.id == orderId }!!
    }

    override suspend fun updateOrderStatus(orderId: Long, status: String): Order {
        val orderDto = ordersNetworkDataSource.updateOrderStatus(orderId, status)
        _orders.value = _orders.value.filterNot { order -> order.id == orderId }
        return orderDto.toOrderEntity().toOrder()
    }
}