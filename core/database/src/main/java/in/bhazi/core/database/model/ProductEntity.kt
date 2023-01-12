package `in`.bhazi.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int
)
