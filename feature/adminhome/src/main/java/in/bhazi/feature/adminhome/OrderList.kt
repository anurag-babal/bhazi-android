package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.design.theme.BhaziTheme
import `in`.bhazi.core.model.Order
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OrderList(
    modifier: Modifier = Modifier,
    orders: List<Order> = listOf()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(orders) { order ->
            AdminOrderItem(
                order = order,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewOrderList() {
    BhaziTheme {
        OrderList(orders = orderList)
    }
}

val orderList: List<Order> = listOf(
    Order(id = 1, status = "ordered", customerName = "ABC"),
    Order(id = 2, status = "ordered", customerName = "Def"),
    Order(id = 3, status = "ordered", customerName = "ghi")
)