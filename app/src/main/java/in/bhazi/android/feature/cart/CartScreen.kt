package `in`.bhazi.android.feature.cart

import `in`.bhazi.core.model.Cart
import `in`.bhazi.core.model.Quantity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CartRoute(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartUiState by viewModel.cartUiState.collectAsState()

    CartScreen(
        onClickIncrement = { viewModel.event(CartEvent.Increment(it)) },
        onClickDecrement = { viewModel.event(CartEvent.Decrement(it)) },
        cartItems = cartUiState.cartItems,
        quantities = cartUiState.quantities,
        isLoading = cartUiState.isLoading,
        error = cartUiState.error,
        modifier = modifier
    )
}

@Composable
fun CartScreen(
    onClickIncrement: (Cart) -> Unit,
    onClickDecrement: (Cart) -> Unit,
    modifier: Modifier = Modifier,
    cartItems: List<Cart> = emptyList(),
    quantities: List<Quantity> = emptyList(),
    isLoading: Boolean = false,
    error: String? = null
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (cartItems.isEmpty()) {
            Text(
                text = "Cart is empty",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn {
                cartItems.forEach { cartItem ->
                    item(key = cartItem.id) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = cartItem.name)
                            `in`.bhazi.core.design.component.QuantitySelector(
                                quantity = quantities.find {
                                    it.productId == cartItem.id
                                }?.quantity ?: 0,
                                onClickIncrement = { onClickIncrement(cartItem) },
                                onClickDecrement = { onClickDecrement(cartItem) }
                            )
                        }
                    }
                }
            }
            if (isLoading)
                CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}