package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.model.Product
import `in`.bhazi.core.network.model.ProductDto
import `in`.bhazi.core.network.retrofit.ProductRemoteDataSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ViewModel() {
    val products: StateFlow<List<Product>> = flow {
        productRemoteDataSource.fetchProducts().map {
            emit(it.map { productDto -> productDto.toProduct() })
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        listOf()
    )

    val product: StateFlow<Product> = flow {
        emit(productRemoteDataSource.fetchProduct(68).toProduct())
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        Product(0, "abc", 0)
    )

    private fun ProductDto.toProduct(): Product {
        return Product(id = id, name = name, price = price)
    }
}