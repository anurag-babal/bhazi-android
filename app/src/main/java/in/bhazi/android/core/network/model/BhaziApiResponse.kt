package `in`.bhazi.android.core.network.model

data class BhaziApiResponse<T>(
    val status: Int,
    val count: Int,
    val data: T,
    val message: String
)
