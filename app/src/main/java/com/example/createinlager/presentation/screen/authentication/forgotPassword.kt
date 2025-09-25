package com.example.createinlager.presentation.screen.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleAuth

@Composable
fun forgotPassword(navController: NavController){

    Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.block))) {

        Column(modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {

            Spacer(modifier = Modifier.height(66.dp))

            buttonBack(navController, "SingIn")

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Забыл Пароль",
                style = TitleAuth,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.text),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Введите свою учетную запись\n" +
                        " для сброса",
                style = TextOnBoardTypeSmall,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.hint),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            val email = textFieldAunth("ly4dov.s@yandex.ru", true, 0)

            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { },
                modifier = Modifier
                    .padding(top = 24.dp)
                    .height(50.dp)
                    .fillMaxSize(),
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = colorResource(R.color.disable),
                    containerColor = colorResource(R.color.accent),
                    contentColor = colorResource(R.color.block)
                )
            ){
                Text("Отправить", style = ButtonText, color = colorResource(R.color.block))
            }
        }
    }
}

@Composable
fun MassageEmail(){

    AlertDialog(
        onDismissRequest = { true },
        containerColor = Color.White,
        icon = {
            Box(modifier = Modifier.size(40.dp))
            Icon(
                ImageBitmap.imageResource(R.drawable.email),
                contentDescription = "Информация о приложении",
                tint = Color(0xFF48B2E7),
                modifier = Modifier.height(30.dp).width(30.dp)
            )
        },
        title = {
            Text(
                text = "Проверьте Ваш Email",
                fontSize = 16.sp,
                color = Color(0xff2B2B2B),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
        },
        text = {
            Text(
                "Мы отправили код восстановления пароля на вашу электронную почту.",
                style = TextOnBoardTypeSmall,
                color = Color(0xff707B81),
                modifier = Modifier.fillMaxWidth(),
            )
        },
        confirmButton = {null}
    )
}

@Preview
@Composable
fun testMessage(){
    MassageEmail()
}
