package `in`.bhazi.android.core.database

import `in`.bhazi.android.core.database.dao.CartDao
import `in`.bhazi.android.core.database.dao.ProductDao
import `in`.bhazi.android.core.database.dao.QuantityDao
import `in`.bhazi.android.core.database.model.CartEntity
import `in`.bhazi.android.core.database.model.ProductEntity
import `in`.bhazi.android.core.database.model.QuantityEntity
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