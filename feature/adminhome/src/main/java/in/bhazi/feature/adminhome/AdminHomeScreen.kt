package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.design.component.DropDownMenu
import `in`.bhazi.core.model.Order
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AdminHomeRoute(
    viewModel: AdminHomeViewModel = hiltViewModel()
) {
    val statuses = viewModel.statuses
    val selectedStatus: String = viewModel.selectedStatus
    val types = viewModel.types
    val selectedType: String = viewModel.selectedType

    val orders by viewModel.orders.collectAsState()

    AdminHomeScreen(
        orders = orders,
        statuses = statuses,
        selectedStatus = selectedStatus,
        types = types,
        selectedType = selectedType,
        onClickStatusItem = { viewModel.onClickMenuItem(status = it) },
        onClickTypeItem = { viewModel.onClickMenuItem(type = it) },
        modifier = Modifier
    )
}

@Composable
fun AdminHomeScreen(
    onClickStatusItem: (String) -> Unit,
    onClickTypeItem: (String) -> Unit,
    modifier: Modifier = Modifier,
    statuses: List<String> = listOf(),
    selectedStatus: String = "",
    types: List<String> = listOf(),
    selectedType: String = "",
    orders: List<Order> = listOf()
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                DropDownMenu(
                    options = types,
                    selectedOption = selectedType,
                    onClickMenuItem = onClickTypeItem,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 8.dp, end = 4.dp)
                )
                DropDownMenu(
                    options = statuses,
                    selectedOption = selectedStatus,
                    onClickMenuItem = onClickStatusItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 8.dp)
                )
            }
            OrderList(orders = orders)
        }
    }
}