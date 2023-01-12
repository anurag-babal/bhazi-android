package `in`.bhazi.core.model

data class Order(
    val id: Int = 0,
    val status: String = "",
    val amount: Double = 0.0,
    val deliveryCharge: Double = 0.0,
    val packagingCharge: Double = 0.0,
    val walletBalance: Double = 0.0,
    val couponBalance: Double = 0.0,
    val referralWalletBalance: Double = 0.0,
    val paymentMode: String = "",
    val deliveryType: String = "",
    val packagingType: String = "",
    val deliveryTimePref: String = "",
    val addressNew: Address = Address(),
    val shopId: Int = 0,
    val shopName: String = "",
    val customerName: String = "",
    val mobileNumber: String = "",
    val orderItems: List<OrderItem> = listOf(),
    val address: String = "",
    val timestamp: String = ""
)