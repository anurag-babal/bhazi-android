package `in`.bhazi.core.domain

import `in`.bhazi.core.model.Quantity
import `in`.bhazi.core.data.repository.QuantityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuantityFlowUseCase @Inject constructor(
    private val quantityRepository: QuantityRepository
) {
    operator fun invoke(productId: Int): Flow<Quantity?> {
        return quantityRepository.getByProductIdFlow(productId)
    }
}