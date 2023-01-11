package `in`.bhazi.android.core.database.dao

import `in`.bhazi.android.core.database.model.ProductEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(productEntities: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(productEntity: ProductEntity)

    @Query("delete from productentity")
    suspend fun deleteProducts()

    @Query("select * from productentity")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("select * from productentity where lower(name) like '%'||:query||'%'")
    fun searchProducts(query: String): Flow<List<ProductEntity>>

    @Query("select * from productentity where id = :id")
    fun getProductFlow(id: Int): Flow<ProductEntity>

    @Query("select * from productentity where id = :id")
    suspend fun getProduct(id: Int): ProductEntity

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(product: ProductEntity)
}