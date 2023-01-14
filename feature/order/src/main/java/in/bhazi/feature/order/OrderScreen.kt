package `in`.bhazi.feature.order

import `in`.bhazi.core.model.Order
import `in`.bhazi.core.model.OrderItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
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
        onClickStatusBtn = { viewModel.onClickStatus(it) }
    )
}

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    order: Order? = null,
    isLoading: Boolean = false,
    onClickStatusBtn: (String) -> Unit = {}
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
                OrderDetailHeader(
                    customerName = order.customerName,
                    mobileNumber = order.mobileNumber,
                    modifier = Modifier.layoutId("header")
                )
                OrderDetailBody(
                    orderItems = order.orderItems,
                    modifier = Modifier.layoutId("body")
                )
                OrderDetailFooter(
                    paymentMode = order.paymentMode,
                    deliveryTimePref = order.deliveryTimePref,
                    modifier = Modifier.layoutId("footer"),
                    status = order.status,
                    onClickStatusBtn = onClickStatusBtn
                )
            }
        }
    }
}

@Composable
fun OrderDetailHeader(
    modifier: Modifier = Modifier,
    customerName: String = "John Doe",
    mobileNumber: String = "9876543210"
) {
    Card(modifier = modifier.fillMaxWidth()) {
        MyColumn(modifier = Modifier.fillMaxWidth()) {
            Text(text = customerName)
            Text(text = mobileNumber)
        }
    }
}

@Composable
fun OrderDetailBody(
    modifier: Modifier = Modifier,
    orderItems: List<OrderItem> = listOf()
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
    ) {
        items(items = orderItems, key = { it.id }) { item: OrderItem ->
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
}

@Composable
fun MyColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.padding(8.dp),
        content = content
    )
}

@Composable
fun OrderDetailFooter(
    modifier: Modifier = Modifier,
    paymentMode: String = "",
    deliveryTimePref: String = "",
    status: String = "",
    onClickStatusBtn: (String) -> Unit = {}
) {
    Card(modifier = modifier.fillMaxWidth()) {
        MyColumn(modifier = Modifier.fillMaxWidth()) {
            Text(text = paymentMode)
            Text(text = deliveryTimePref)
            OrderStatusUpdateBtn(
                label = getNewStatus(status),
                enabled = status == "Ordered" || status == "Out for delivery",
                onClickStatusBtn = onClickStatusBtn
            )
        }
    }
}

@Composable
fun OrderStatusUpdateBtn(
    modifier: Modifier = Modifier,
    label: String = "Button",
    enabled: Boolean = false,
    onClickStatusBtn: (String) -> Unit = {}
) {
    Button(
        onClick = { onClickStatusBtn(label) },
        enabled = enabled,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = label, textAlign = TextAlign.Center)
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val header = createRefFor("header")
        val body = createRefFor("body")
        val footer = createRefFor("footer")

        constrain(header) { top.linkTo(parent.top, margin) }
        constrain(body) {
            top.linkTo(header.bottom, margin = margin)
            bottom.linkTo(footer.top, margin = margin)
            height = Dimension.fillToConstraints
        }
        constrain(footer) { bottom.linkTo(parent.bottom, margin = margin) }
    }
}

fun getNewStatus(status: String): String {
    return when (status) {
        "Ordered" -> "Out for delivery"
        "Out for delivery" -> "Delivered"
        else -> status
    }
}

enum class OrderStatus(
    val displayName: String,
    val description: String
) {
    ORDERED("Ordered", "Ordered"),
    OUT_FOR_DELIVERY("Out For Delivery", "Out for delivery"),
    DELIVERED("Delivered", "Delivered"),
    CANCELLED("Cancelled", "Cancelled")
}