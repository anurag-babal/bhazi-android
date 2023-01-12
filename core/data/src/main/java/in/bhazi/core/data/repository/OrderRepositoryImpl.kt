package `in`.bhazi.core.data.repository

import `in`.bhazi.core.data.model.toOrder
import `in`.bhazi.core.data.model.toOrderEntity
import `in`.bhazi.core.model.Order
import `in`.bhazi.core.network.retrofit.OrdersNetworkDataSource
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val ordersNetworkDataSource: OrdersNetworkDataSource
) : OrderRepository {
    override suspend fun getOrders(
        day: String,
        status: String,
        page: Int,
        size: Int
    ): List<Order> {
        val remoteOrders = ordersNetworkDataSource
            .fetchOrdersForAdmin(
                status = status,
                type = day,
                page = page,
                size = size
            )
        return remoteOrders.map { it.toOrderEntity().toOrder() }
    }
}