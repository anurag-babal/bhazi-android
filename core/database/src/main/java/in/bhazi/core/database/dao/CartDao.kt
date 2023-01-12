package `in`.bhazi.core.database.dao

import `in`.bhazi.core.database.model.CartEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartEntity: CartEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(cartEntity: CartEntity)

    @Delete
    suspend fun delete(cartEntity: CartEntity)

    @Query("delete from cartentity")
    suspend fun deleteAll()

    @Query("select * from cartentity")
    fun getAll(): Flow<List<CartEntity>>
}