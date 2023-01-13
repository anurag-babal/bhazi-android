package `in`.bhazi.core.data.model

import `in`.bhazi.core.database.model.OrderEntity
import `in`.bhazi.core.model.Order
import `in`.bhazi.core.network.model.OrderDto

fun OrderEntity.toOrder(): Order {
    return Order(
        id = id,
        status = status,
        amount = amount,
        deliveryCharge = deliveryCharge,
        packagingCharge = packagingCharge,
        walletBalance = walletBalance,
        couponBalance = couponBalance,
        referralWalletBalance = referralWalletBalance,
        paymentMode = paymentMode,
        deliveryType = deliveryType,
        packagingType = packagingType,
        deliveryTimePref = deliveryTimePref,
        address = address.toAddress(),
        shopId = shopId,
        shopName = shopName,
        customerName = customerName,
        mobileNumber = mobileNumber,
        orderItems = orderItems.toOrderItems(),
        timestamp = timestamp
    )
}

fun List<OrderEntity>.toOrders(): List<Order> {
    return map { it.toOrder() }
}

fun OrderDto.toOrderEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        status = status,
        amount = amount,
        deliveryCharge = deliveryCharge,
        packagingCharge = packagingCharge,
        walletBalance = walletBalance,
        couponBalance = couponBalance,
        referralWalletBalance = referralWalletBalance,
        paymentMode = paymentMode,
        deliveryType = deliveryType,
        packagingType = packagingType,
        deliveryTimePref = deliveryTimePref,
        address = address.toAddressEntity(),
        shopId = shopId,
        shopName = shopName ?: "",
        customerName = customerName,
        mobileNumber = mobileNumber ?: "",
        orderItems = orderItems.toOrderItemEntities(),
        timestamp = timestamp
    )
}

fun List<OrderDto>.toOrderEntities(): List<OrderEntity> {
    return map { it.toOrderEntity() }
}