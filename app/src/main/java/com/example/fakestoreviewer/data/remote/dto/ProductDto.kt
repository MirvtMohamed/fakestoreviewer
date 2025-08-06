package com.example.fakestoreviewer.data.remote.dto
import kotlinx.serialization.Serializable
import com.example.fakestoreviewer.domain.model.Product

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val images: List<String>
)
fun ProductDto.toProduct() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    imageUrl = images.firstOrNull() ?: ""
)
