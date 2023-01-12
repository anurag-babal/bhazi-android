package `in`.bhazi.android.feature.product

import `in`.bhazi.core.model.Product
import `in`.bhazi.android.feature.product.navigation.productIdArg
import `in`.bhazi.core.domain.DecrementQuantityUseCase
import `in`.bhazi.core.domain.GetProductFlowUseCase
import `in`.bhazi.core.domain.GetQuantityFlowUseCase
import `in`.bhazi.core.domain.IncrementQuantityUseCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductFlowUseCase: GetProductFlowUseCase,
    private val getQuantityFlowUseCase: GetQuantityFlowUseCase,
    private val incrementQuantityUseCase: IncrementQuantityUseCase,
    private val decrementQuantityUseCase: DecrementQuantityUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val productId: Int = checkNotNull(savedStateHandle.get<String>(productIdArg)).toInt()

    val uiState: StateFlow<ProductUiState> = getProductFlowUseCase(productId)
        .combine(getQuantityFlowUseCase(productId)) { product, quantity ->
            ProductUiState(product = product, quantity = quantity?.quantity ?: 0)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2_000),
            ProductUiState(isLoading = true)
        )

    fun event(event: ProductEvent) {
        when(event) {
            is ProductEvent.OnClickIncrement -> {
                incrementQuantity(event.productId)
            }
            is ProductEvent.OnClickDecrement -> {
                decrementQuantity(event.productId)
            }
        }
    }

    private fun incrementQuantity(productId: Int) {
        viewModelScope.launch {
            incrementQuantityUseCase(productId)
        }
    }

    private fun decrementQuantity(productId: Int) {
        viewModelScope.launch {
            decrementQuantityUseCase(productId)
        }
    }
}

sealed interface ProductEvent {
    data class OnClickIncrement(val productId: Int) : ProductEvent
    data class OnClickDecrement(val productId: Int) : ProductEvent
}

data class ProductUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val quantity: Byte = 0,
    val error: String? = null
)
