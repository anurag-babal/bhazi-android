package `in`.bhazi.android.core.domain

import `in`.bhazi.android.core.data.repository.QuantityRepository
import `in`.bhazi.android.core.model.Quantity
import javax.inject.Inject

class DecrementQuantityUseCase @Inject constructor(
    private val quantityRepository: QuantityRepository,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase
) {
    suspend operator fun invoke(productId: Int) {
        val quantity1: Quantity? = quantityRepository.getByProductId(productId)
        quantity1?.let {
            val quantity = it.quantity - 1
            if (quantity > 0) {
                quantityRepository.update(
                    Quantity(
                        productId,
                        quantity.toByte()
                    )
                )
            } else if (quantity == 0) {
                removeProductFromCartUseCase(productId)
                quantityRepository.delete(
                    Quantity(
                        productId,
                        quantity.toByte()
                    )
                )
            }
        }
    }
}