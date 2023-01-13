package `in`.bhazi.core.data.model

import `in`.bhazi.core.database.model.AddressEntity
import `in`.bhazi.core.model.Address
import `in`.bhazi.core.network.model.AddressDto

fun AddressEntity.toAddress(): Address {
    return Address(
        id = id,
        floor = floor,
        completeAddress = completeAddress,
        instruction = instruction,
        distanceFromShop = distanceFromShop,
        latitude = latitude,
        longitude = longitude
    )
}

fun AddressDto.toAddressEntity(): AddressEntity {
    return AddressEntity(
        id = id,
        floor = floor,
        completeAddress = completeAddress,
        instruction = instruction,
        distanceFromShop = distanceFromShop,
        latitude = latitude ?: 0.0.toBigDecimal(),
        longitude = longitude ?: 0.0.toBigDecimal()
    )
}