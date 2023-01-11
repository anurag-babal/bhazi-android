package `in`.bhazi.android.feature.product.navigation

import `in`.bhazi.android.feature.product.ProductRoute
import androidx.navigation.*
import androidx.navigation.compose.composable

const val productIdArg = "productId"

fun NavController.navigateToProduct(productId: Int) {
    this.navigate("product_route/$productId")
}

fun NavGraphBuilder.productScreen() {
    composable(
        route = "product_route/{$productIdArg}",
        arguments = listOf(
            navArgument(productIdArg) { type = NavType.StringType }
        )
    ) {
        ProductRoute()
    }
}