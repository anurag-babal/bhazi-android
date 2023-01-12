package `in`.bhazi.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)
