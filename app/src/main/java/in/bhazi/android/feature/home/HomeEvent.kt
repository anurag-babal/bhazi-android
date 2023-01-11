package `in`.bhazi.android.feature.home

import `in`.bhazi.core.model.Product

sealed class HomeEvent {
    object OnRefresh: HomeEvent()
    data class OnClickIncrement(val product: Product): HomeEvent()
    data class OnClickDecrement(val product: Product): HomeEvent()
    data class OnSearchQueryChange(val query: String): HomeEvent()
}