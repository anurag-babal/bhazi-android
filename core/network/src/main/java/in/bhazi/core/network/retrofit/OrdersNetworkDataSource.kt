package `in`.bhazi.core.network.retrofit

import `in`.bhazi.core.network.model.BhaziApiResponse
import `in`.bhazi.core.network.model.OrderDto
import javax.inject.Inject

class OrdersNetworkDataSource @Inject constructor(
    private val bhaziApi: BhaziApi
) {
    suspend fun fetchOrdersForAdmin(
        status: String,
        type: String,
        page: Int = 0,
        size: Int = 20
    ): List<OrderDto> {
        val response: BhaziApiResponse<List<OrderDto>> = bhaziApi
                .getOrders(status, type.lowercase(), page, size)
        return response.data
    }
}