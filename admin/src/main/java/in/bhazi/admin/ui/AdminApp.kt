package `in`.bhazi.admin.ui

import `in`.bhazi.admin.AdminNavHost
import `in`.bhazi.admin.navigation.TopLevelDestination
import `in`.bhazi.core.design.theme.BhaziTheme
import `in`.bhazi.feature.adminhome.navigateAdminHomeRoute
import `in`.bhazi.feature.adminhome.navigateToAdminHome
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

@Composable
fun AdminApp() {
    BhaziTheme {
        val navController = rememberNavController()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                AdminBottomBar(
                    destinations = TopLevelDestination.values().toList(),
                    currentDestination = navController.currentBackStackEntryAsState().value?.destination,
                    onNavigateToDestination = { navigation(navController, it) }
                )
            }
        ) { innerPadding ->
            AdminNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

fun navigation(navController: NavController, topLevelDestination: TopLevelDestination) {
    val navOptions = navOptions {
        popUpTo(navController.graph.startDestinationId)
        launchSingleTop = true
    }

    when(topLevelDestination) {
        TopLevelDestination.HOME -> {
            navController.navigateToAdminHome(navOptions)
        }
        TopLevelDestination.ACCOUNT -> {
            navController.navigateToScreenB(navOptions)
        }
    }
}

fun NavController.navigateToScreenB(navOptions: NavOptions) {
    navigate(route = accountRoute, navOptions = navOptions)
}

fun NavGraphBuilder.screenB() {
    composable(route = accountRoute) {
        ScreenB()
    }
}

const val accountRoute = "account_route"

@Composable
fun ScreenB(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Blue)) {
        Text(text = "Inside ScreenB")
    }
}