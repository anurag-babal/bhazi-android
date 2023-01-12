package `in`.bhazi.android.feature.home

import `in`.bhazi.core.domain.DecrementQuantityUseCase
import `in`.bhazi.core.domain.GetProductsUseCase
import `in`.bhazi.core.domain.IncrementQuantityUseCase
import `in`.bhazi.core.domain.RefreshProductsUseCase
import `in`.bhazi.core.data.repository.QuantityRepository
import `in`.bhazi.core.model.Product
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val refreshProductsUseCase: RefreshProductsUseCase,
    private val incrementQuantityUseCase: IncrementQuantityUseCase,
    private val decrementQuantityUseCase: DecrementQuantityUseCase,
    private val quantityRepository: QuantityRepository
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = getProductsUseCase("")
        .combine(quantityRepository.getAll()) { products, quantities ->
            HomeUiState(products = products, quantities = quantities)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2_000),
            HomeUiState(isLoading = true)
        )

    var state by mutableStateOf(HomeUiState())
    private var searchJob: Job? = null

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnRefresh -> {
                refreshProducts()
            }
            is HomeEvent.OnClickIncrement -> {
                incrementQuantity(event.product)
            }
            is HomeEvent.OnClickDecrement -> {
                decrementQuantity(event.product)
            }
            is HomeEvent.OnSearchQueryChange -> {
                searchProduct(event.query)
            }
        }
    }

    private fun getProducts(
        query: String = state.searchQuery?.lowercase() ?: ""
    ) {
        viewModelScope.launch {
            getProductsUseCase(query)
        }
    }

    private fun refreshProducts() {
        viewModelScope.launch {
            refreshProductsUseCase()
        }
    }

    private fun searchProduct(query: String) {
        state = state.copy(searchQuery = query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getProducts()
        }
    }

    private fun incrementQuantity(product: Product) {
        viewModelScope.launch {
            incrementQuantityUseCase(product.id)
        }
    }

    private fun decrementQuantity(product: Product) {
        viewModelScope.launch {
            decrementQuantityUseCase(product.id)
        }
    }
}