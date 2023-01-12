package `in`.bhazi.core.network.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AddressDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("floor")
    val floor: String?,
    @SerializedName("completeAddress")
    val completeAddress: String?,
    @SerializedName("instruction")
    val instruction: String?,
    @SerializedName("distanceFromShop")
    val distanceFromShop: Double?,
    @SerializedName("lattitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)