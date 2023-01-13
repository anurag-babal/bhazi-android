package `in`.bhazi.core.database.model

import androidx.room.Entity
import java.math.BigDecimal

@Entity
data class AddressEntity(
    val id: Long,
    val floor: String?,
    val completeAddress: String,
    val instruction: String?,
    val distanceFromShop: Double,
    val latitude: BigDecimal,
    val longitude: BigDecimal
)
