package `in`.bhazi.android.feature.product

import `in`.bhazi.core.model.Product
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val uiState: ProductUiState by viewModel.uiState.collectAsState()
    ProductScreen(
        product = uiState.product,
        quantity = uiState.quantity,
        isLoading = uiState.isLoading,
        error = uiState.error,
        onClickIncrement = { viewModel.event(ProductEvent.OnClickIncrement(it)) },
        onClickDecrement = { viewModel.event(ProductEvent.OnClickDecrement(it)) },
        modifier = modifier
    )
}

@Composable
fun ProductScreen(
    onClickIncrement: (Int) -> Unit,
    onClickDecrement: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
    product: Product? = null,
    quantity: Byte = 0,
    error: String? = null
) {
    Box(modifier = modifier.fillMaxSize()) {
        product?.let {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = it.name)
                `in`.bhazi.core.design.component.QuantitySelector(
                    quantity = quantity,
                    onClickIncrement = { onClickIncrement(it.id) },
                    onClickDecrement = { onClickDecrement(it.id) })
            }
        }
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        error?.let {
            Text(text = it)
        }
    }
}