package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.design.theme.BhaziTheme
import `in`.bhazi.core.model.Order
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AdminOrderItem(
    order: Order,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "${order.id}")
            Text(text = order.status)
            Text(text = order.customerName)
        }
    }
}

@Preview
@Composable
fun PreviewAdminOrderItem() {
    BhaziTheme {

    }
}