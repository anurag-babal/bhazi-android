package `in`.bhazi.android.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuantityEntity(
    @PrimaryKey
    val productId: Int,
    val quantity: Byte
)
