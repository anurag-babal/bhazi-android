package `in`.bhazi.core.network.retrofit

import `in`.bhazi.core.network.model.BhaziApiResponse
import `in`.bhazi.core.network.model.ProductDto
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): BhaziApiResponse<List<ProductDto>>?

    @GET("products/{id}")
    suspend fun getProduct(id: Int): BhaziApiResponse<ProductDto>?
}