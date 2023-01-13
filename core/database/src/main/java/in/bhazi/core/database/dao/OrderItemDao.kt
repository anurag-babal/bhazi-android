package `in`.bhazi.core.database.dao

import `in`.bhazi.core.database.model.OrderItemEntity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface OrderItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(orderItem: OrderItemEntity)

    @Query("select * from orderitementity where id = :orderId")
    fun getAllOrderItemsFlow(orderId: Long): Flow<List<OrderItemEntity>>
}