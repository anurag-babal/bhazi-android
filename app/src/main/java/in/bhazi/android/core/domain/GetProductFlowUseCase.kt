package `in`.bhazi.android.core.domain

import `in`.bhazi.core.model.Product
import `in`.bhazi.android.core.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductFlowUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(productId: Int): Flow<Product> {
        return productRepository.getProductFlow(productId)
    }
}