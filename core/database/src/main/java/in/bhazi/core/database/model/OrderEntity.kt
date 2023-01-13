package `in`.bhazi.core.database.model

import androidx.room.Entity

@Entity
data class OrderEntity(
    val id: Long,
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
    val address: AddressEntity,
    val shopId: Int,
    val shopName: String = "",
    val customerName: String,
    val mobileNumber: String,
    val orderItems: List<OrderItemEntity>,
    val timestamp: String
)
