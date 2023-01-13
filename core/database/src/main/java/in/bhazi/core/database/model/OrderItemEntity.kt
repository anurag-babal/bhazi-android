package `in`.bhazi.core.database.model

import androidx.room.Entity

@Entity
data class OrderItemEntity(
    val id: Long,
    val productName: String,
    val quantity: Int,
    val price: Int
)
