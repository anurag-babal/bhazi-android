package `in`.bhazi.core.domain

import `in`.bhazi.core.data.repository.QuantityRepository
import javax.inject.Inject

class GetProductQuantityUseCase @Inject constructor(
    private val quantityRepository: QuantityRepository
) {
    suspend operator fun invoke(productId: Int): Byte {
        return quantityRepository.getByProductId(productId)?.quantity ?: 0
    }
}