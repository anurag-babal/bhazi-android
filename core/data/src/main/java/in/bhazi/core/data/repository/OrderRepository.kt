package `in`.bhazi.core.data.repository

import `in`.bhazi.core.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun getOrderDetail(orderId: Long): Order
    suspend fun refreshOrders(day: String, status: String, page: Int, size: Int)
    fun getOrdersFlow(): Flow<List<Order>>
    suspend fun updateOrderStatus(orderId: Long, status: String): Order
}