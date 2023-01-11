package `in`.bhazi.android.feature.home

import `in`.bhazi.android.R
import `in`.bhazi.core.model.Product
import `in`.bhazi.core.model.Quantity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProductList(
    onIncrement: (Product) -> Unit,
    onDecrement: (Product) -> Unit,
    navigateToProduct: (Int) -> Unit,
    modifier: Modifier = Modifier,
    products: List<Product> = emptyList(),
    quantities: List<Quantity> = emptyList(),
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = products,
            key = { item: Product -> item.id }
        ) { product ->
            ProductListItem(
                name = product.name,
                quantity = quantities.find { it.productId == product.id }?.quantity ?: 0,
                price = product.price,
                image = painterResource(id = R.drawable.onion_red),
                incrementQuantity = { onIncrement(product) },
                decrementQuantity = { onDecrement(product) },
                onClickProduct = { navigateToProduct(product.id) }
            )
        }
    }
}
