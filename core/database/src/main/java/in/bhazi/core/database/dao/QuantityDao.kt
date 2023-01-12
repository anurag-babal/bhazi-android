package `in`.bhazi.core.database.dao

import `in`.bhazi.core.database.model.QuantityEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuantityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(quantityEntity: QuantityEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(quantityEntity: QuantityEntity)

    @Delete
    suspend fun delete(quantityEntity: QuantityEntity)

    @Query("select * from quantityentity")
    fun getAll(): Flow<List<QuantityEntity>>

    @Query("select * from quantityentity where productId = :productId")
    fun getByProductIdFlow(productId: Int): Flow<QuantityEntity?>

    @Query("select * from quantityentity where productId = :productId")
    suspend fun getByProductId(productId: Int): QuantityEntity?
}