package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.design.component.MyGoogleMap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val mapRoute = "map_route"
const val orderIdArg = "orderId"

fun NavController.navigateToMap(orderId: Long) {
    navigate(route = "$mapRoute/$orderId")
}

fun NavGraphBuilder.mapScreen() {
    composable(
        route = "$mapRoute/{$orderIdArg}",
        arguments = listOf(navArgument(name = orderIdArg) { type = NavType.LongType })
    ) {
        MapRoute()
    }
}

@Composable
fun MapRoute(
    viewModel: MapViewModel = hiltViewModel()
) {
    val order by viewModel.order.collectAsState()
    MapScreen(
        latitude = order?.address?.latitude?.toDouble() ?: 0.0,
        longitude = order?.address?.longitude?.toDouble() ?: 0.0,
        title = order?.let { "${it.id}, ${it.paymentMode}" } ?: ""
    )
}

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    title: String = "Address"
) {
    MyGoogleMap(
        latitude = latitude,
        longitude = longitude,
        title = title,
        modifier = modifier
    )
}