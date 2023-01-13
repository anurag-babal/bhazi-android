package `in`.bhazi.core.model

data class OrderItem(
    val id: Long,
    val productName: String,
    val quantity: Int,
    val price: Int
)