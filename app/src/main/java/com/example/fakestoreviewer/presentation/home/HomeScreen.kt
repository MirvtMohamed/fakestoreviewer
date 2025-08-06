package com.example.fakestoreviewer.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.fakestoreviewer.util.Result
import com.example.fakestoreviewer.domain.model.Product
import com.example.fakestoreviewer.presentation.components.ProductCard



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
            SnackbarHost(hostState = remember { SnackbarHostState() }) {
                Text("Error: ${(state as Result.Error).message}")
            }
        }
    }
}
