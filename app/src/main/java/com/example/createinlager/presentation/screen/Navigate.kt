package com.example.createinlager.presentation.screen

import android.telecom.Call
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.presentation.screen.OnBoards.OnBoards
import com.example.createinlager.presentation.screen.Profile.BarcodeFullScreen
import com.example.createinlager.presentation.screen.Profile.Profile
import com.example.createinlager.presentation.screen.Profile.SideMenu
import com.example.createinlager.presentation.screen.authentication.CreateNewPassword
import com.example.createinlager.presentation.screen.authentication.RegisterAcc
import com.example.createinlager.presentation.screen.authentication.SingIn
import com.example.createinlager.presentation.screen.authentication.Verification
import com.example.createinlager.presentation.screen.authentication.forgotPassword
import com.example.createinlager.presentation.screen.markets.Cart
import com.example.createinlager.presentation.screen.markets.Category
import com.example.createinlager.presentation.screen.markets.Favorites
import com.example.createinlager.presentation.screen.markets.Home
import com.example.createinlager.presentation.screen.markets.Search
import com.example.createinlager.presentation.screen.markets.SneakersDetails

@Composable
fun Navigate() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "OnBoards"
    ) {
        composable("OnBoards") { OnBoards(navController) }
        composable("RegisterAcc") { RegisterAcc(navController) }
        composable("SingIn") { SingIn(navController) }
        composable("forgotPassword") { forgotPassword(navController) }
        composable("Verification/{email}/{otp}") {  backStackEntry ->
            // Получаем переданный текст
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            Verification(email, otp, navController)
        }
        composable("CreateNewPassword/{userId}/{token}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?: ""
            val token =  backStackEntry.arguments?.getString("token")?: ""
            CreateNewPassword(userId,token,  navController) }
        composable("Home/{userIdToHome}/{tokenToHome}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToHome")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToHome")?: ""
            Home(userId,token,  navController) }
        composable("CatalogSneakers/{typeCross}/{userIdToHomeTWO}/{tokenToHomeTWO}") { backStackEntry ->
            val typeCross = backStackEntry.arguments?.getString("typeCross") ?: ""
            val userId = backStackEntry.arguments?.getString("userIdToHomeTWO")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToHomeTWO")?: ""
            Category(typeCross = typeCross,userId, token,navController) }
        composable("Details/{sneakersId}/{userIdToDetails}/{tokenToDetails}/{CurretidFavorite}/{CurretidInCart}") { backStackEntry ->
            val sneakersId = backStackEntry.arguments?.getString("sneakersId")?: ""
            val userId = backStackEntry.arguments?.getString("userIdToDetails")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToDetails")?: ""
            val CurretidFavorite = backStackEntry.arguments?.getString("CurretidFavorite")?: ""
            val CurretidInCart = backStackEntry.arguments?.getString("CurretidInCart")?: ""
            SneakersDetails(sneakersId,userId, token, CurretidFavorite,CurretidInCart,navController) }
        composable("Favorite/{userIdToFavorite}/{tokenToFavorite}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToFavorite")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToFavorite")?: ""
            Favorites(userId,token,  navController)
        }
        composable("Search/{userIdToSearch}/{tokenToSearch}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToSearch")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToSearch")?: ""
            Search(userId,token,  navController)
        }
        composable("Cart/{userIdToCart}/{tokenToCart}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToCart")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToCart")?: ""
            Cart(userId,token,  navController)
        }
        composable("Profile/{userIdToProfile}/{tokenToProfile}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToProfile")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToProfile")?: ""
            SideMenu(userId,token,  navController)
        }
        composable("ProfileScreen/{userIdToProfileScreen}/{tokenToProfileScreen}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToProfileScreen")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToProfileScreen")?: ""
            Profile(userId,token,  navController)
        }
        composable("BarcodeFullScreen/{userIdToBarcodeFullScreen}/{tokenToBarcodeFullScreen}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToBarcodeFullScreen")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToBarcodeFullScreen")?: ""
            BarcodeFullScreen(userId,token,navController)
        }

    }
}
