package `in`.bhazi.core.domain

import `in`.bhazi.core.model.Product
import `in`.bhazi.core.data.repository.ProductRepository
import `in`.bhazi.core.data.repository.QuantityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val quantityRepository: QuantityRepository
) {
    operator fun invoke(query: String): Flow<List<Product>> {
        return productRepository.getProducts(query)
    }
}