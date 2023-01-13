package `in`.bhazi.core.domain

import `in`.bhazi.core.data.repository.OrderRepository
import `in`.bhazi.core.model.Order
import javax.inject.Inject

class GetOrderDetailUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(orderId: Long): Order {
        return orderRepository.getOrderDetail(orderId)
    }
}