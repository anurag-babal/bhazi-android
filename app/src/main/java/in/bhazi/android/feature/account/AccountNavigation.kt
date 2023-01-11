package `in`.bhazi.android.feature.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val accountNavigationRoute = "account_route"

fun NavController.navigateToAccount(navOptions: NavOptions? = null) {
    this.navigate(accountNavigationRoute, navOptions)
}

fun NavGraphBuilder.accountScreen() {
    composable(route = accountNavigationRoute) {
        AccountRoute()
    }
}