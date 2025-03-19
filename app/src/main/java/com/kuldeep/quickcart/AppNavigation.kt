package com.kuldeep.quickcart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.kuldeep.quickcart.pages.CategoryProductsPage
import com.kuldeep.quickcart.screen.AuthScreen
import com.kuldeep.quickcart.screen.HomeScreen
import com.kuldeep.quickcart.screen.LoginScreen
import com.kuldeep.quickcart.screen.SignupScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    GlobalNavigation.navController = navController
    val isLoggedIn = Firebase.auth.currentUser != null
    val firstPage = if (isLoggedIn) "home" else "auth"

    NavHost(navController = navController, startDestination = firstPage) {
        composable("auth") {
            AuthScreen(modifier, navController)
        }

        composable("login") {
            LoginScreen(modifier, navController)
        }
        composable("signup") {
            SignupScreen(modifier, navController)
        }
        composable("home") {
            HomeScreen(modifier, navController)
        }
        composable("category-products/{categoryId}") {
            val categoryId=it.arguments?.getString("categoryId")
            CategoryProductsPage(modifier,categoryId?:"")
        }
    }
}

object GlobalNavigation {
    lateinit var navController: NavHostController
}