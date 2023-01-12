package `in`.bhazi.core.data.model

import `in`.bhazi.core.database.model.OrderItemEntity
import `in`.bhazi.core.model.OrderItem
import `in`.bhazi.core.network.model.OrderItemDto

fun OrderItemEntity.toOrderItem(): OrderItem {
    return OrderItem(
        id = id,
        productName = productName,
        quantity = quantity,
        price = price
    )
}

fun List<OrderItemEntity>.toOrderItems(): List<OrderItem> {
    return map { it.toOrderItem() }
}

fun OrderItemDto.toOrderItemEntity(): OrderItemEntity {
    return OrderItemEntity(
        id = id,
        productName = productName,
        quantity = quantity,
        price = price
    )
}

fun List<OrderItemDto>.toOrderItemEntities(): List<OrderItemEntity> {
    return map { it.toOrderItemEntity() }
}