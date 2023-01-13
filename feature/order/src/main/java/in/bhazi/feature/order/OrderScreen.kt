package `in`.bhazi.feature.order

import `in`.bhazi.core.model.Order
import `in`.bhazi.core.model.OrderItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OrderRoute(
    viewModel: OrderViewModel = hiltViewModel()
) {
    val orderUiState by viewModel.uiState.collectAsState()

    OrderScreen(
        isLoading = orderUiState.loading,
        order = orderUiState.order,
        enabled = orderUiState.order?.status == "Ordered",
        onClickDelivered = { viewModel.onClickDelivered() }
    )
}

@Composable
fun OrderScreen(
    onClickDelivered: () -> Unit,
    modifier: Modifier = Modifier,
    order: Order? = null,
    isLoading: Boolean = false,
    enabled: Boolean = false
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (order == null) {
            Text(text = "No order detail to show")
        } else {
            ConstraintLayout(
                constraintSet = constraints,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = order.customerName, modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("name"))
                Text(
                    text = order.mobileNumber,
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId("mobile")
                )
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 16.dp),
                    modifier = Modifier.layoutId("items")
                ) {
                    items(items = order.orderItems, key = { it.id }) { item: OrderItem ->
                        val quantity: Double
                        val weightSuffix: String

                        if (item.quantity < 1000) {
                            quantity = item.quantity.toDouble()
                            weightSuffix = "Gm"
                        } else {
                            quantity = (item.quantity / 1000.0)
                            weightSuffix = "Kg"
                        }

                        OrderItemsItem(
                            productName = item.productName,
                            quantity = quantity,
                            weightSuffix = weightSuffix,
                            price = item.price,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }
                }
                Text(
                    text = order.paymentMode,
                    modifier = Modifier
                        .layoutId("payment")
                )
                Text(
                    text = order.deliveryTimePref,
                    modifier = Modifier
                        .layoutId("deliveryTime")
                )
                Button(
                    onClick = onClickDelivered,
                    enabled = enabled,
                    modifier = Modifier.fillMaxWidth().layoutId("button")
                ) {
                    Text(text = "Delivered", textAlign = TextAlign.Center)
                }
            }
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val name = createRefFor("name")
        val mobileNumber = createRefFor("mobile")
        val items = createRefFor("items")
        val paymentMode = createRefFor("payment")
        val deliveryTime = createRefFor("deliveryTime")

        constrain(name) { top.linkTo(parent.top, margin) }
        constrain(mobileNumber) { top.linkTo(name.bottom, margin = margin) }
        constrain(items) {
            top.linkTo(mobileNumber.bottom, margin = margin)
            bottom.linkTo(paymentMode.top, margin = margin)
            height = Dimension.fillToConstraints
        }
        constrain(paymentMode) { bottom.linkTo(deliveryTime.top, margin = margin) }
        constrain(deliveryTime) { bottom.linkTo(button.top, margin = margin) }
        constrain(button) { bottom.linkTo(parent.bottom, margin = margin) }
    }
}