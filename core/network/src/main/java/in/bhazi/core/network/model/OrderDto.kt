package `in`.bhazi.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OrderDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("status")
    val status: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("deliveryCharge")
    val deliveryCharge: Double,
    @SerializedName("packagingCharge")
    val packagingCharge: Double,
    @SerializedName("walletBalance")
    val walletBalance: Double,
    @SerializedName("couponBalance")
    val couponBalance: Double,
    @SerializedName("referralWalletBalance")
    val referralWalletBalance: Double,
    @SerializedName("paymentMode")
    val paymentMode: String,
    @SerializedName("deliveryType")
    val deliveryType: String,
    @SerializedName("packagingType")
    val packagingType: String,
    @SerializedName("deliveryTimePref")
    val deliveryTimePref: String,
    @SerializedName("addressNew")
    val address: AddressDto,
    @SerializedName("shopId")
    val shopId: Int,
    @SerializedName("shopName")
    val shopName: String?,
    @SerializedName("customerName")
    val customerName: String,
    @SerializedName("mobileNumber")
    val mobileNumber: String?,
    @SerializedName("orderItems")
    val orderItems: List<OrderItemDto>,
    @SerializedName("timestamp")
    val timestamp: String
)