package `in`.bhazi.android.core.data.model

import `in`.bhazi.android.core.database.model.ProductEntity
import `in`.bhazi.core.model.Cart
import `in`.bhazi.core.model.Product
import `in`.bhazi.core.network.model.ProductDto

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        price = price
    )
}

fun List<ProductEntity>.toProducts(): List<Product> {
    return map { it.toProduct() }
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price
    )
}

fun Product.toCart(): Cart {
    return Cart(
        id = id,
        name = name
    )
}

fun ProductDto.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price
    )
}

fun List<ProductDto>.toProductEntities(): List<ProductEntity> {
    return map { it.toProductEntity() }
}