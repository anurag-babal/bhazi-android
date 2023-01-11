package `in`.bhazi.android.core.domain

import `in`.bhazi.android.core.common.Resource
import `in`.bhazi.android.core.model.Product
import `in`.bhazi.android.core.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Product>>> {
        repository.refreshProducts()
        return flow {
            emit(Resource.Loading(true))
            repository.refreshProducts()?.let {
                emit(Resource.Error(it.exceptionOrNull()?.localizedMessage ?: "Unknown Error"))
            }
            emit(Resource.Loading(false))
        }
    }
}