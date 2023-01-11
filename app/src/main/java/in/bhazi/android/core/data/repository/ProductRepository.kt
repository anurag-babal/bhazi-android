package `in`.bhazi.android.core.data.repository

import `in`.bhazi.android.core.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(query: String): Flow<List<Product>>
    suspend fun refreshProducts(): Result<List<Product>>?
    fun getProductFlow(id: Int): Flow<Product>
    suspend fun getProduct(id: Int): Product
    suspend fun updateProduct(product: Product)
}