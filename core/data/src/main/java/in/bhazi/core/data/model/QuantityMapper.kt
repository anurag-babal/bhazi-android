package `in`.bhazi.core.data.model

import `in`.bhazi.core.database.model.QuantityEntity
import `in`.bhazi.core.model.Quantity

fun QuantityEntity.toQuantity(): Quantity {
    return Quantity(
        productId = productId,
        quantity = quantity
    )
}

fun List<QuantityEntity>.toQuantities(): List<Quantity> {
    return map { it.toQuantity() }
}

fun Quantity.toQuantityEntity(): QuantityEntity {
    return QuantityEntity(
        productId = productId,
        quantity = quantity
    )
}