package `in`.bhazi.core.network.retrofit

import `in`.bhazi.core.network.model.BhaziApiResponse
import `in`.bhazi.core.network.model.OrderDto
import `in`.bhazi.core.network.model.UpdateOrderStatusDto
import android.util.Log
import javax.inject.Inject

class OrdersNetworkDataSource @Inject constructor(
    private val bhaziApi: BhaziApi
) {
    private val TAG = javaClass.simpleName

    suspend fun fetchOrdersForAdmin(
        status: String,
        type: String,
        page: Int = 0,
        size: Int = 20
    ): List<OrderDto>? {
        var response: BhaziApiResponse<List<OrderDto>>? = null
        try {
            response = bhaziApi.getOrders(status, type.lowercase(), page, size)
        } catch (e: Exception) {
            Log.d(TAG, "fetchOrdersForAdmin: ${e.localizedMessage}")
        }
        return response?.data
    }

    suspend fun fetchOrderDetail(orderId: Long): OrderDto? {
        val response: BhaziApiResponse<OrderDto>? = bhaziApi.getOrderDetail(orderId)
        return response?.data
    }

    suspend fun updateOrderStatus(orderId: Long, status: String): OrderDto? {
        var response: BhaziApiResponse<OrderDto>? = null
        try {
            response = bhaziApi
                .updateOrderStatus(orderId, UpdateOrderStatusDto(status))
        } catch (e: Exception) {
            Log.d(TAG, "updateOrderStatus: ${e.localizedMessage}")
        }
        return response?.data
    }
}