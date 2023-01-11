package `in`.bhazi.feature.adminhome

import `in`.bhazi.core.model.Product
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AdminHomeRoute(
    viewModel: AdminHomeViewModel = hiltViewModel()
) {
    val product by viewModel.product.collectAsState()
    val products by viewModel.products.collectAsState()
    AdminHomeScreen(product = product, products = products, modifier = Modifier)
}

@Composable
fun AdminHomeScreen(
    product: Product,
    modifier: Modifier = Modifier,
    products: List<Product> = listOf()
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = product.name, modifier = Modifier.fillMaxWidth().heightIn(min = 20.dp))
        /*LazyColumn(
            modifier = Modifier
        ) {
            items(products) { product ->
                Text(text = product.name, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }*/
    }
}