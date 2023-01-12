package `in`.bhazi.core.model

data class Order(
    val id: Int,
    val status: String,
    val amount: Double,
    val deliveryCharge: Double,
    val packagingCharge: Double,
    val walletBalance: Double,
    val couponBalance: Double,
    val referralWalletBalance: Double,
    val paymentMode: String,
    val deliveryType: String,
    val packagingType: String,
    val deliveryTimePref: String,
    val address: Address,
    val shopId: Int,
    val shopName: String,
    val customerName: String,
    val mobileNumber: String,
    val orderItems: List<OrderItem>,
    val timestamp: String
)