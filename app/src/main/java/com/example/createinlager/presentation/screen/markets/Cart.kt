package com.example.createinlager.presentation.screen.markets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.theme.ui.TitleCategoryType

@Composable
fun Cart(userId: String, token: String, navController: NavController){

    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.background)).padding(horizontal = 20.dp)) {

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.padding(start = 21.dp, top = 48.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            buttonBack(navController, "Home/${userId}/${token}")

            Text(
                text = "Корзина",
                modifier = Modifier.fillMaxWidth(),
                style = TitleCategoryType,
                textAlign = TextAlign.Center
            )
        }
    }


}
