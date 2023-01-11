package `in`.bhazi.android.feature.home

import `in`.bhazi.core.design.component.SearchBar
import `in`.bhazi.android.core.model.Product
import `in`.bhazi.android.core.model.Quantity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeRoute(
    navigateToProduct: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    HomeScreen(
        isLoading = homeUiState.isLoading,
        isRefreshing = homeUiState.isRefreshing,
        products = homeUiState.products,
        quantities = homeUiState.quantities,
        error = homeUiState.error,
        searchQuery = homeUiState.searchQuery,
        onRefresh = { viewModel.onEvent(HomeEvent.OnRefresh) },
        onIncrement = { viewModel.onEvent(HomeEvent.OnClickIncrement(it)) },
        onDecrement = { viewModel.onEvent(HomeEvent.OnClickDecrement(it)) },
        navigateToProduct = navigateToProduct,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    onRefresh: () -> Unit,
    onIncrement: (Product) -> Unit,
    onDecrement: (Product) -> Unit,
    navigateToProduct: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    isLoading: Boolean = true,
    products: List<Product> = emptyList(),
    quantities: List<Quantity> = emptyList(),
    error: String? = null,
    searchQuery: String? = null
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = isRefreshing
    )
    Box(modifier = modifier.fillMaxSize()) {
        if (products.isEmpty()) {
            Button(onClick = onRefresh, modifier = Modifier.align(Alignment.Center)) {
                Text(text = "Referesh")
            }
        } else {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                `in`.bhazi.core.design.component.SearchBar()
                Spacer(modifier = Modifier.height(8.dp))
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = onRefresh
                ) {
                    ProductList(
                        products = products,
                        quantities = quantities,
                        onIncrement = onIncrement,
                        onDecrement = onDecrement,
                        navigateToProduct = navigateToProduct
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            error?.let { error ->
                Text(text = error)
            }
        }
    }
}