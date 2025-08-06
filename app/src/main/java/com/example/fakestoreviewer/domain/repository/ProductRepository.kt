package com.example.fakestoreviewer.domain.repository

import com.example.fakestoreviewer.domain.model.Product
import com.example.fakestoreviewer.util.Result

interface ProductRepository {
    suspend fun getAllProducts(): com.example.fakestoreviewer.util.Result<List<Product>>
    suspend fun getProductById(id: Int): Result<Product>
}