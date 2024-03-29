package `in`.bhazi.core.domain

import `in`.bhazi.core.model.Product
import `in`.bhazi.core.data.repository.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Product {
        return productRepository.getProduct(productId)
    }
}