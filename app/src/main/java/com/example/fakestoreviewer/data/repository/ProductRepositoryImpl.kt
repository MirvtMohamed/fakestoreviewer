package com.example.fakestoreviewer.data.repository
import com.example.fakestoreviewer.domain.model.Product
import com.example.fakestoreviewer.data.remote.api.ProductApi
import com.example.fakestoreviewer.data.remote.dto.toProduct
import com.example.fakestoreviewer.domain.repository.ProductRepository
import com.example.fakestoreviewer.util.Result

class ProductRepositoryImpl(private val api: ProductApi): ProductRepository {
    override suspend fun getAllProducts(): Result<List<Product>> = try {
        Result.Success(api.getProducts().map { it.toProduct() })
    } catch (e: Exception) {
        Result.Error(e.message ?: "Unknown error")
    }

    override suspend fun getProductById(id: Int): Result<Product> = try {
        Result.Success(api.getProductById(id).toProduct())
    } catch (e: Exception) {
        Result.Error(e.message ?: "Unknown error")
    }
}