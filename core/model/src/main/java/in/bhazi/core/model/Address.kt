package `in`.bhazi.core.model

data class Address(
    val id: Int = 0,
    val floor: String = "",
    val completeAddress: String = "",
    val instruction: String = "",
    val distanceFromShop: Double = 0.0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)