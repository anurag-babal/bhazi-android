package `in`.bhazi.admin

import `in`.bhazi.admin.ui.screenB
import `in`.bhazi.feature.adminhome.adminHomeScreen
import `in`.bhazi.feature.adminhome.adminHomeGraph
import `in`.bhazi.feature.adminhome.mapScreen
import `in`.bhazi.feature.adminhome.navigateToMap
import `in`.bhazi.feature.order.navigateToOrder
import `in`.bhazi.feature.order.orderScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AdminNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = adminHomeGraph
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        adminHomeScreen(
            navigateToOrder = { orderId ->
                navController.navigateToOrder(orderId)
            },
            navigateToAddress = { orderId ->
                navController.navigateToMap(orderId)
            },
            nestedGraph = {
                orderScreen()
                mapScreen()
            }
        )
        screenB()
    }
}