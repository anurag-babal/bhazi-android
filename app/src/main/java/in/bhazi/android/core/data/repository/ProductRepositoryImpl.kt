package `in`.bhazi.android.core.data.repository

import `in`.bhazi.android.core.data.model.toProduct
import `in`.bhazi.android.core.data.model.toProductEntities
import `in`.bhazi.android.core.data.model.toProductEntity
import `in`.bhazi.android.core.data.model.toProducts
import `in`.bhazi.core.database.dao.ProductDao
import `in`.bhazi.core.model.Product
import `in`.bhazi.core.network.retrofit.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override fun getProducts(query: String): Flow<List<Product>> {
        return productDao.searchProducts(query).map { it.toProducts() }
    }

    override suspend fun refreshProducts(): Result<List<Product>>? {
        val remoteProducts = productRemoteDataSource.fetchProducts()
        return if (remoteProducts.isSuccess) {
            remoteProducts.getOrNull()?.let {
                productDao.deleteProducts()
                productDao.insert(it.toProductEntities())
            }
            null
        } else {
            Result.failure(remoteProducts.exceptionOrNull()!!)
        }
    }

    override fun getProductFlow(id: Int): Flow<Product> {
        return productDao.getProductFlow(id).map { it.toProduct() }
    }

    override suspend fun getProduct(id: Int): Product {
        return productDao.getProduct(id).toProduct()
    }

    override suspend fun updateProduct(product: Product) {
        productDao.update(product.toProductEntity())
    }
}