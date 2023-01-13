package `in`.bhazi.core.domain

import `in`.bhazi.core.data.repository.OrderRepository
import javax.inject.Inject

class RefreshOrdersUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(
        day: String,
        status: String,
        page: Int = 0,
        size: Int = 20
    ) {
        orderRepository.refreshOrders(day, status, page, size)
    }
}