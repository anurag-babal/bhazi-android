package `in`.bhazi.android.feature.home

import `in`.bhazi.android.R
import `in`.bhazi.core.design.component.QuantitySelector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ProductListItem(
    name: String,
    quantity: Byte,
    price: Int,
    image: Painter,
    incrementQuantity: () -> Unit,
    decrementQuantity: () -> Unit,
    onClickProduct: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        modifier = modifier
            .clickable { onClickProduct() }
            .height(72.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            Column(
                modifier = Modifier.align(Alignment.Top)
            ) {
                Text(
                    text = "₹ ${price}/-",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                if (quantity > 0) {
                    `in`.bhazi.core.design.component.QuantitySelector(
                        quantity = quantity,
                        onClickDecrement = decrementQuantity,
                        onClickIncrement = incrementQuantity
                    )
                } else {
                    Button(onClick = incrementQuantity) {
                        Text(
                            text = stringResource(id = R.string.add_to_cart)
                        )
                    }
                }
            }
        }
    }
}
