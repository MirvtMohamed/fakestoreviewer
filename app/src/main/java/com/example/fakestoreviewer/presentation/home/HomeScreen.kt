package com.example.fakestoreviewer.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fakestoreviewer.util.Result
import com.example.fakestoreviewer.domain.model.Product
import com.example.fakestoreviewer.presentation.components.ProductCard
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is Result.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is Result.Success -> {
            LazyColumn {
                items((state as Result.Success<List<Product>>).data) { product ->
                    ProductCard(product = product) {
                        navController.navigate("detail/${product.id}")
                    }
                }
            }
        }
        is Result.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${(state as Result.Error).message}", color = Color.Red)
            }
        }

    }
}
