package `in`.bhazi.core.data.repository

import `in`.bhazi.core.data.model.toOrder
import `in`.bhazi.core.data.model.toOrderEntity
import `in`.bhazi.core.model.Order
import `in`.bhazi.core.network.model.OrderDto
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
        remoteOrders?.let {
            _orders.value = it.map { orderDto -> orderDto.toOrderEntity().toOrder() }
        }
    }

    override fun getOrdersFlow(): Flow<List<Order>> {
        return _orders
    }

    override suspend fun getOrderDetail(orderId: Long): Order {
        return _orders.value.find { order -> order.id == orderId }!!
    }

    override suspend fun updateOrderStatus(orderId: Long, status: String): Order {
        val orderDto: OrderDto? = ordersNetworkDataSource.updateOrderStatus(orderId, status)
        orderDto?.let {
            _orders.value = _orders.value.filterNot { order -> order.id == orderId }
            return it.toOrderEntity().toOrder()
        }
        return _orders.value.find { order -> order.id == orderId }!!
    }
}