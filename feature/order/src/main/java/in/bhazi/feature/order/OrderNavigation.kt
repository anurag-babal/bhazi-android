package `in`.bhazi.feature.order

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val orderRoute = "order_route"
const val orderIdArg = "orderId"

fun NavController.navigateToOrder(orderId: Long) {
    this.navigate("$orderRoute/$orderId")
}

fun NavGraphBuilder.orderScreen() {
    composable(
        route = "$orderRoute/{$orderIdArg}",
        arguments = listOf(
            navArgument(orderIdArg) { type = NavType.LongType }
        )
    ) {
        OrderRoute()
    }
}