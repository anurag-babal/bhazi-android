package `in`.bhazi.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class OrderItemDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("price")
    val price: Int
)