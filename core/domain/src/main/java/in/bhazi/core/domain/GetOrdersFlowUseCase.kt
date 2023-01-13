package `in`.bhazi.core.domain

import `in`.bhazi.core.data.repository.OrderRepository
import `in`.bhazi.core.model.Order
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersFlowUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(): Flow<List<Order>> {
        return orderRepository.getOrdersFlow()
    }
}