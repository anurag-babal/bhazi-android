package `in`.bhazi.core.domain

import `in`.bhazi.core.common.Resource
import `in`.bhazi.core.data.repository.OrderRepository
import `in`.bhazi.core.model.Order
import javax.inject.Inject

class RefreshOrdersUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(
        day: String,
        status: String,
        page: Int = 0,
        size: Int = 20
    ): Resource<List<Order>> {
        val orders = orderRepository.getOrders(day, status, page, size)
        return Resource.Success(orders)
    }
}