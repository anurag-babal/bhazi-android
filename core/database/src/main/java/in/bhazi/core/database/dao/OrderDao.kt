package `in`.bhazi.core.database.dao

import `in`.bhazi.core.database.model.OrderEntity
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface OrderDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(order: OrderEntity)

    @Query("select * from orderentity")
    fun getAllOrdersFlow(): Flow<List<OrderEntity>>
}