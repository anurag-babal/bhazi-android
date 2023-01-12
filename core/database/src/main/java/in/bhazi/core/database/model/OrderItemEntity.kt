package `in`.bhazi.core.database.model

import androidx.room.Entity

@Entity
data class OrderItemEntity(
    val id: Int,
    val productName: String,
    val quantity: Int,
    val price: Int
)
