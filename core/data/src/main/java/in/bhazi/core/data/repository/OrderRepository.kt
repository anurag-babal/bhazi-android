package `in`.bhazi.core.data.repository

import `in`.bhazi.core.model.Order

interface OrderRepository {
    suspend fun getOrders(
        day: String,
        status: String,
        page: Int,
        size: Int
    ): List<Order>
}