package `in`.bhazi.core.database

import `in`.bhazi.core.database.dao.CartDao
import `in`.bhazi.core.database.dao.ProductDao
import `in`.bhazi.core.database.dao.QuantityDao
import `in`.bhazi.core.database.model.CartEntity
import `in`.bhazi.core.database.model.ProductEntity
import `in`.bhazi.core.database.model.QuantityEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProductEntity::class,
        CartEntity::class,
        QuantityEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
    abstract val cartDao: CartDao
    abstract val quantityDao: QuantityDao
}