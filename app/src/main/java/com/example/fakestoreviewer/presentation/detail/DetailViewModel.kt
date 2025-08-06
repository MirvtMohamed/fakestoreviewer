package com.example.fakestoreviewer.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreviewer.domain.model.Product
import com.example.fakestoreviewer.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.fakestoreviewer.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _product = MutableStateFlow<Result<Product>>(Result.Loading)
    val product: StateFlow<Result<Product>> = _product

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            _product.value = repository.getProductById(id)
        }
    }
}