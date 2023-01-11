package `in`.bhazi.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberBhaziAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): BhaziAppState {
    return remember(navController, coroutineScope) {
        BhaziAppState(
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}

class BhaziAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    private val TAG: String? = BhaziAppState::class.simpleName
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().toList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reelecting the same item
            launchSingleTop = true
            // Restore state when reelecting a previously selected item
            restoreState = true
        }

        when(topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHomeGraph(topLevelNavOptions)
            TopLevelDestination.CART -> navController.navigateToCart(topLevelNavOptions)
            TopLevelDestination.REFERRAL -> navController.navigateToReferral(topLevelNavOptions)
            TopLevelDestination.ACCOUNT -> navController.navigateToAccount(topLevelNavOptions)
        }
    }
}