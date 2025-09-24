package com.example.createinlager.presentation.screen.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

fun SingIn(){

    ButtonBack(61,navController,"RegAcc")

    Column(modifier = Modifier.padding(top = 121.dp, start = 20.dp, end = 20.dp).fillMaxWidth()) {

        TitleAunth("Привет!", "Заполните Свои Данные")

        Spacer(modifier = Modifier.height(54.dp))

        namesInterfaceElements("Email")

        val email = textFieldAunth("xyz@gmail.com")

        Spacer(modifier = Modifier.height(30.dp))

        namesInterfaceElements("Пароль")

        val password = passwordFieldAunth("xxxxxxxx")

        Text("Востановить", textAlign = TextAlign.End, modifier = Modifier.padding(top = 12.dp).fillMaxWidth().clickable { navController.navigate("ForgPass") }, style = miniTextButton)

        ButtonSingIn(email, password, navController)

        Box(modifier = Modifier.padding(top = 100.dp, bottom = 47.dp).fillMaxSize(), contentAlignment = Alignment.BottomCenter){

            Row { Text("Вы впервые?", style = RegularTextTypeONB, color = colorResource(R.color.hint))

                Text(" Создать", style = RegularTextTypeONB, color = colorResource(R.color.text), modifier = Modifier.clickable { navController.navigate("RegAcc") })
            }
        }
    }


}
