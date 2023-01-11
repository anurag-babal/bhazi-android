package `in`.bhazi.android.feature.home

import `in`.bhazi.android.core.model.Product
import `in`.bhazi.android.core.model.Quantity

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val quantities: List<Quantity> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String? = null,
    val error: String? = null
)