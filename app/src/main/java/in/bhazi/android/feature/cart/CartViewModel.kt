package `in`.bhazi.android.feature.cart

import `in`.bhazi.core.domain.DecrementQuantityUseCase
import `in`.bhazi.core.domain.IncrementQuantityUseCase
import `in`.bhazi.core.data.repository.CartRepository
import `in`.bhazi.core.data.repository.QuantityRepository
import `in`.bhazi.core.model.Cart
import `in`.bhazi.core.model.Quantity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val quantityRepository: QuantityRepository,
    private val incrementQuantityUseCase: IncrementQuantityUseCase,
    private val decrementQuantityUseCase: DecrementQuantityUseCase
) : ViewModel() {
    val cartUiState: StateFlow<CartUiState> =
        cartRepository.getAll()
            .combine(quantityRepository.getAll()) { cartItems, quantities ->
                CartUiState(cartItems = cartItems, quantities = quantities)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(2_000),
                CartUiState(isLoading = true)
            )

    fun event(event: CartEvent) {
        when(event) {
            is CartEvent.Increment -> onClickIncrement(event.cartItem)
            is CartEvent.Decrement -> onClickDecrement(event.cartItem)
        }
    }

    private fun onClickIncrement(cartItem: Cart) {
        viewModelScope.launch {
            incrementQuantityUseCase(cartItem.id)
        }
    }

    private fun onClickDecrement(cartItem: Cart) {
        viewModelScope.launch {
            decrementQuantityUseCase(cartItem.id)
        }
    }
}

data class CartUiState(
    val isLoading: Boolean = false,
    val cartItems: List<Cart> = emptyList(),
    val quantities: List<Quantity> = emptyList(),
    val error: String? = null
)

sealed interface CartEvent {
    data class Increment(val cartItem: Cart): CartEvent
    data class Decrement(val cartItem: Cart): CartEvent
}