package `in`.bhazi.android.core.domain

import `in`.bhazi.android.core.data.model.toCart
import `in`.bhazi.android.core.data.repository.CartRepository
import `in`.bhazi.android.core.model.Product
import javax.inject.Inject

class RemoveProductFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val getProductUseCase: GetProductUseCase
) {
    suspend operator fun invoke(productId: Int) {
        val product: Product = getProductUseCase(productId)
        cartRepository.delete(product.toCart())
    }
}