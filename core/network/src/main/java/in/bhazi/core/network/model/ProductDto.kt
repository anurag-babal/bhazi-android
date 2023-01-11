package `in`.bhazi.core.network.model

data class ProductDto(
    var id: Int,
    var price: Int,
    var name: String,
    var description: String? = null,
    var baseQuantity: String? = null,
    var soldByPiece: String? = null,
    var type: String? = null,
    var subType: String? = null,
    var numberOfPieces: Int? = null,
    var outOfStock: Boolean? = null
)