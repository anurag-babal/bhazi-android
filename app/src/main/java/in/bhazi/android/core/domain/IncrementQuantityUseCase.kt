package `in`.bhazi.android.core.domain

import `in`.bhazi.android.core.data.repository.QuantityRepository
import `in`.bhazi.android.core.model.Quantity
import javax.inject.Inject

class IncrementQuantityUseCase @Inject constructor(
    private val quantityRepository: QuantityRepository,
    private val addProductToCartUseCase: AddProductToCartUseCase
) {
    suspend operator fun invoke(productId: Int) {
        val quantity: Quantity? = quantityRepository.getByProductId(productId)
        if (quantity == null) {
            addProductToCartUseCase(productId)
            quantityRepository.insert(Quantity(productId, 1.toByte()))
        } else {
            quantityRepository.update(
                Quantity(
                    productId,
                    quantity.quantity.plus(1).toByte()
                )
            )
        }
    }
}