package com.example.createinlager.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.createinlager.presentation.screen.OnBoards.OnBoards
import com.example.createinlager.presentation.screen.authentication.CreateNewPassword
import com.example.createinlager.presentation.screen.authentication.RegisterAcc
import com.example.createinlager.presentation.screen.authentication.SingIn
import com.example.createinlager.presentation.screen.authentication.Verification
import com.example.createinlager.presentation.screen.authentication.forgotPassword
import com.example.createinlager.presentation.screen.markets.Home

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
        composable("Home/{userId}/{token}") {backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userIdToHome")?: ""
            val token =  backStackEntry.arguments?.getString("tokenToHome")?: ""
            Home(userId,token,  navController) }



    }
}
