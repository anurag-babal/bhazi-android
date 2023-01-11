package `in`.bhazi.android.core.data.model

import `in`.bhazi.android.core.database.model.CartEntity
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