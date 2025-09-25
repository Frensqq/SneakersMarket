package com.example.createinlager.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.createinlager.presentation.screen.OnBoards.OnBoards
import com.example.createinlager.presentation.screen.authentication.RegisterAcc
import com.example.createinlager.presentation.screen.authentication.SingIn
import com.example.createinlager.presentation.screen.authentication.forgotPassword

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



    }
}
