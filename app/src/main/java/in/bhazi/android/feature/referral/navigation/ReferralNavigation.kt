package `in`.bhazi.android.feature.referral.navigation

import `in`.bhazi.android.feature.referral.ReferralRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val referralNavigationRoute = "referral"

fun NavController.navigateToReferral(navOptions: NavOptions? = null) {
    this.navigate(referralNavigationRoute, navOptions)
}

fun NavGraphBuilder.referralScreen() {
    composable(route = referralNavigationRoute) {
        ReferralRoute()
    }
}