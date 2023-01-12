package `in`.bhazi.core.data.repository

import `in`.bhazi.core.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun insert(cart: Cart)
    suspend fun update(cart: Cart)
    suspend fun delete(cart: Cart)
    fun getAll(): Flow<List<Cart>>
}