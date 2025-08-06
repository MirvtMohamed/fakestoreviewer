package com.example.fakestoreviewer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreviewer.domain.model.Product
import com.example.fakestoreviewer.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.fakestoreviewer.util.Result
import jakarta.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<Result<List<Product>>>(Result.Loading)
    val uiState: StateFlow<Result<List<Product>>> = _uiState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = repository.getAllProducts()
        }
    }
}