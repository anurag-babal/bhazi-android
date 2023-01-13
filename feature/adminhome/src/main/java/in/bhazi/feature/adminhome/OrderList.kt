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
    onClickOrder: (Long) -> Unit,
    modifier: Modifier = Modifier,
    orders: List<Order> = listOf()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(items = orders, key = { it.id }) { order ->
            AdminOrderItem(
                order = order,
                onClickOrder = { onClickOrder(order.id) },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewOrderList() {
    BhaziTheme {

    }
}