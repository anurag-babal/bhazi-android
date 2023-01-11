package `in`.bhazi.android.feature.cart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val cartNavigationRoute = "cart_route"

fun NavController.navigateToCart(navOptions: NavOptions? = null) {
    this.navigate(cartNavigationRoute, navOptions)
}

fun NavGraphBuilder.cartScreen() {
    composable(cartNavigationRoute) {
        CartRoute()
    }
}