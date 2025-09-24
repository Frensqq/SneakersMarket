package com.example.createinlager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.createinlager.presentation.screen.Navigate
import com.example.createinlager.presentation.theme.ui.CreateInLagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreateInLagerTheme {
                Navigate()
            }
        }
    }
}