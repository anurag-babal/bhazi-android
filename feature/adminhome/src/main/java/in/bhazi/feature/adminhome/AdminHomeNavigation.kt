package `in`.bhazi.feature.adminhome

import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

const val adminHomeGraph = "admin_home_graph"
private const val adminHomeRoute = "admin_home_route"

fun NavController.navigateToAdminHome(navOptions: NavOptions) {
    this.navigate(adminHomeGraph, navOptions)
}

fun NavGraphBuilder.adminHomeScreen(
    navigateToOrder: (Long) -> Unit,
    navigateToAddress: (Long) -> Unit,
    nestedGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = adminHomeRoute,
        route = adminHomeGraph
    ) {
        composable(adminHomeRoute) {
            AdminHomeRoute(
                onClickOrder = navigateToOrder,
                onClickAddress = navigateToAddress
            )
        }
        nestedGraph()
    }
}