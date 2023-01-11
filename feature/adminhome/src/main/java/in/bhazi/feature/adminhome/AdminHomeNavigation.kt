package `in`.bhazi.feature.adminhome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val navigateAdminHomeRoute = "admin_home_route"

fun NavController.navigateToAdminHome(navOptions: NavOptions) {
    this.navigate(navigateAdminHomeRoute, navOptions)
}

fun NavGraphBuilder.adminHomeScreen() {
    composable(route = navigateAdminHomeRoute) {
        AdminHomeRoute()
    }
}