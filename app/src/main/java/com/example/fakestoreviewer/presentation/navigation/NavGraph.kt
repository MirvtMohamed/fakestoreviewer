package com.example.fakestoreviewer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fakestoreviewer.presentation.detail.DetailScreen
import com.example.fakestoreviewer.presentation.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("detail/{productId}", arguments = listOf(navArgument("productId") { type = NavType.IntType })) {
            val productId = it.arguments?.getInt("productId") ?: return@composable
            DetailScreen(productId = productId)
        }
    }
}
