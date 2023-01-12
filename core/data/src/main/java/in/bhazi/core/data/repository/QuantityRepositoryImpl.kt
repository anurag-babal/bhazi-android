package `in`.bhazi.core.data.repository

import `in`.bhazi.core.data.model.toQuantities
import `in`.bhazi.core.data.model.toQuantity
import `in`.bhazi.core.data.model.toQuantityEntity
import `in`.bhazi.core.database.dao.QuantityDao
import `in`.bhazi.core.model.Quantity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuantityRepositoryImpl @Inject constructor(
    private val quantityDao: QuantityDao
) : QuantityRepository {
    override suspend fun insert(quantity: Quantity) {
        quantityDao.insert(quantity.toQuantityEntity())
    }

    override suspend fun update(quantity: Quantity) {
        quantityDao.update(quantity.toQuantityEntity())
    }

    override suspend fun delete(quantity: Quantity) {
        quantityDao.delete(quantity.toQuantityEntity())
    }

    override fun getAll(): Flow<List<Quantity>> {
        return quantityDao.getAll().map { it.toQuantities() }
    }

    override fun getByProductIdFlow(productId: Int): Flow<Quantity?> {
        return quantityDao.getByProductIdFlow(productId).map { it?.toQuantity() }
    }

    override suspend fun getByProductId(productId: Int): Quantity? {
        return quantityDao.getByProductId(productId)?.toQuantity()
    }
}