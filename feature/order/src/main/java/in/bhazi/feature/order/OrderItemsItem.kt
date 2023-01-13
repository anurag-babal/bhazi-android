package `in`.bhazi.feature.order

import `in`.bhazi.core.design.theme.BhaziTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

@Composable
fun OrderItemsItem(
    modifier: Modifier = Modifier,
    productName: String = "Abc",
    quantity: Double = 0.0,
    weightSuffix: String = "Kg",
    price: Int = 0,
) {
    val df = DecimalFormat("###.###")
    Card(
        backgroundColor = Color.Blue,
        contentColor = Color.White,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = "$productName -> ${df.format(quantity)} $weightSuffix")
            Text(text = "₹ $price")
        }
    }
}

@Preview
@Composable
fun Preview() {
    BhaziTheme {
        OrderItemsItem(
            modifier = Modifier
        )
    }
}