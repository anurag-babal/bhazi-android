package `in`.bhazi.android.core.data.repository

import `in`.bhazi.core.model.Quantity
import kotlinx.coroutines.flow.Flow

interface QuantityRepository {
    suspend fun insert(quantity: Quantity)
    suspend fun update(quantity: Quantity)
    suspend fun delete(quantity: Quantity)
    fun getAll(): Flow<List<Quantity>>
    suspend fun getByProductId(productId: Int): Quantity?
    fun getByProductIdFlow(productId: Int): Flow<Quantity?>
}