package `in`.bhazi.core.database

import `in`.bhazi.core.database.dao.CartDao
import `in`.bhazi.core.database.dao.ProductDao
import `in`.bhazi.core.database.dao.QuantityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao
    }

    @Provides
    @Singleton
    fun provideCartDao(database: AppDatabase): CartDao {
        return database.cartDao
    }

    @Provides
    @Singleton
    fun provideQuantityDao(database: AppDatabase): QuantityDao {
        return database.quantityDao
    }
}