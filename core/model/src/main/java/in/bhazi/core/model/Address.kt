package `in`.bhazi.core.model

import java.math.BigDecimal

data class Address(
    val id: Long,
    val floor: String?,
    val completeAddress: String,
    val instruction: String?,
    val distanceFromShop: Double,
    val latitude: BigDecimal,
    val longitude: BigDecimal
)