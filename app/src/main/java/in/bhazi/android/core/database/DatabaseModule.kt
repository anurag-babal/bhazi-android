package `in`.bhazi.android.core.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DB_PRODUCT = "product.db"

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): AppDatabase {
        return Room
            .databaseBuilder(
                app,
                AppDatabase::class.java,
                DB_PRODUCT
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}