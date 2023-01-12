package `in`.bhazi.core.data.model

import `in`.bhazi.core.database.model.CartEntity
import `in`.bhazi.core.model.Cart

fun CartEntity.toCart(): Cart {
    return Cart(
        id = id,
        name = name
    )
}

fun List<CartEntity>.toCart(): List<Cart> {
    return map { it.toCart() }
}

fun Cart.toCartEntity(): CartEntity {
    return CartEntity(
        id = id,
        name = name
    )
}