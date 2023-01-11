package `in`.bhazi.android.core.data.di

import `in`.bhazi.android.core.data.repository.*
import `in`.bhazi.android.core.data.util.NetworkMonitor
import `in`.bhazi.android.core.data.util.NetworkMonitorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindsProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    fun bindQuantityRepository(
        quantityRepositoryImpl: QuantityRepositoryImpl
    ): QuantityRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: NetworkMonitorImpl
    ): NetworkMonitor
}