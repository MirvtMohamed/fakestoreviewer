package com.example.fakestoreviewer.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.fakestoreviewer.util.Result
import com.example.fakestoreviewer.domain.model.Product


@Composable
fun DetailScreen(
    productId: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadProduct(productId)
    }

    val state by viewModel.product.collectAsState()

    when (state) {
        is Result.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is Result.Success -> {
            val product = (state as Result.Success<Product>).data
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(model = product.imageUrl, contentDescription = product.title, modifier = Modifier.fillMaxWidth().height(250.dp))
                Text(product.title, style = MaterialTheme.typography.titleLarge)
                Text("EGP ${product.price}", style = MaterialTheme.typography.bodyLarge)
                Text(product.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
        is Result.Error -> {
            Text("Error loading product")
        }
    }
}
