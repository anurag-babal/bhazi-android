package `in`.bhazi.android.feature.home.navigation

import `in`.bhazi.android.feature.home.HomeRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val homeGraphRoutePattern = "home_graph"
private const val homeRoute = "home_route"

fun NavController.navigateToHomeGraph(navOptions: NavOptions? = null) {
    this.navigate(homeGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.homeScreen(
    navigateToProduct: (Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = homeGraphRoutePattern,
        startDestination = homeRoute
    ) {
        composable(route = homeRoute) {
            HomeRoute(navigateToProduct = navigateToProduct)
        }
        nestedGraphs()
    }
}