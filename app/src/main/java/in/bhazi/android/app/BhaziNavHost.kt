package `in`.bhazi.android.app

import `in`.bhazi.android.feature.account.accountScreen
import `in`.bhazi.android.feature.cart.cartScreen
import `in`.bhazi.android.feature.product.navigation.navigateToProduct
import `in`.bhazi.android.feature.product.navigation.productScreen
import `in`.bhazi.android.feature.home.navigation.homeGraphRoutePattern
import `in`.bhazi.android.feature.home.navigation.homeScreen
import `in`.bhazi.android.feature.referral.navigation.referralScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun BhaziNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeGraphRoutePattern
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            navigateToProduct = { productId ->
                navController.navigateToProduct(productId)
            },
            nestedGraphs = { productScreen() }
        )
        cartScreen()
        referralScreen()
        accountScreen()
    }
}
