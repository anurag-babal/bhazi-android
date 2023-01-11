package `in`.bhazi.android.core.data.repository

import `in`.bhazi.android.core.data.model.toCart
import `in`.bhazi.android.core.data.model.toCartEntity
import `in`.bhazi.android.core.database.dao.CartDao
import `in`.bhazi.core.model.Cart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {

    override suspend fun insert(cart: Cart) {
        cartDao.insert(cart.toCartEntity())
    }

    override suspend fun update(cart: Cart) {
        cartDao.update(cart.toCartEntity())
    }

    override suspend fun delete(cart: Cart) {
        cartDao.delete(cart.toCartEntity())
    }

    override fun getAll(): Flow<List<Cart>> {
        return cartDao.getAll().map { it.toCart() }
    }
}