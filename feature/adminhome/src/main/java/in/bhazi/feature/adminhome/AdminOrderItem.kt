package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.design.theme.BhaziTheme
import `in`.bhazi.core.model.Order
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdminOrderItem(
    order: Order,
    onClickOrder: () -> Unit,
    onClickAddress: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClickOrder,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "${order.id}")
            Text(text = order.status)
            Text(text = order.customerName)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = order.address.completeAddress,
                    modifier = Modifier.fillMaxWidth(0.80f)
                )
                Button(onClick = onClickAddress) {
                    Text(text = "Map")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAdminOrderItem() {
    BhaziTheme {

    }
}