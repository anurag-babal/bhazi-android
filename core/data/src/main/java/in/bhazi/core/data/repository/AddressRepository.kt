package `in`.bhazi.core.data.repository

import `in`.bhazi.core.model.Address

interface AddressRepository {
    fun getAddressById(id: Long): Address
}