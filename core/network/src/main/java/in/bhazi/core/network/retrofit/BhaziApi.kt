package `in`.bhazi.core.network.retrofit

import `in`.bhazi.core.network.model.BhaziApiResponse
import `in`.bhazi.core.network.model.OrderDto
import `in`.bhazi.core.network.model.ProductDto
import `in`.bhazi.core.network.model.UpdateOrderStatusDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

const val products = "products"
const val orders = "order"
const val productionVersion = "v1"
const val testVersion = "v2"

interface BhaziApi {
    // Products
    @GET(products)
    suspend fun getProducts(): BhaziApiResponse<List<ProductDto>>?

    @GET("$products/{id}")
    suspend fun getProduct(@Path("id") id: Int): BhaziApiResponse<ProductDto>?

    // Orders
    @GET("$orders/{id}")
    suspend fun getOrderDetail(@Path("id") id: Long): BhaziApiResponse<OrderDto>

    @GET("$orders/$productionVersion")
    suspend fun getOrders(
        @Query("status") status: String,
        @Query("type") type: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BhaziApiResponse<List<OrderDto>>

    @PUT("$orders/{id}")
    suspend fun updateOrderStatus(
        @Path("id") orderId: Long,
        @Body updateOrderStatus: UpdateOrderStatusDto
    ): BhaziApiResponse<OrderDto>
}