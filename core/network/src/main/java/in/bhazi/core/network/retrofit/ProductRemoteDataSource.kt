package `in`.bhazi.core.network.retrofit

import `in`.bhazi.core.network.model.BhaziApiResponse
import `in`.bhazi.core.network.model.ProductDto
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val api: ProductApi
) {
   suspend fun fetchProduct(id: Int): ProductDto {
       return try {
           val response: BhaziApiResponse<ProductDto>? = api.getProduct(id)
           response!!.data
       } catch (e: Exception) {
           ProductDto(0, 0, e.message ?: "Unknown error")
       }
   }

    suspend fun fetchProducts(): Result<List<ProductDto>> {
        val response: BhaziApiResponse<List<ProductDto>>?
        val random = listOf(1, 2, 3).random()
        return try {
            /*if (random == 1) {
                delay(5000)
                throw IOException("I/O Exception")
            }
            delay(500)*/
            response = api.getProducts()
            Result.success(response!!.data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}