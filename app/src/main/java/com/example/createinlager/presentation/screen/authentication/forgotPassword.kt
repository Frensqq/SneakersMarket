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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.domain.state.ResultState
import com.example.createinlager.presentation.screen.AccentLongButton
import com.example.createinlager.presentation.screen.ErrorAunth
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.textFieldAunth
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleAuth

@Composable
fun forgotPassword(navController: NavController, viewModel: UserViewModel = viewModel ()){

    val result = viewModel.resultEmailState.collectAsState()

    val email = remember { mutableStateOf("") }

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

            email.value = textFieldAunth("xyz@gmail.com", true, 0).value

            Spacer(modifier = Modifier.height(40.dp))

            AccentLongButton({viewModel.sendOTPCode(email.value) }, "Отправить", true)

        }
        when (result.value) {
            is ResultState.Error -> {
                ErrorAunth((result.value as ResultState.Error).message, "Ошибка отправки Otp кода")
            }
            ResultState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize(0.5f),
                        strokeWidth = 10.dp,
                        color = colorResource(R.color.accent))
                }
            }
            ResultState.Initialized -> {
            }
            is ResultState.Success -> {

                val otp = viewModel.otpCode.value
                MassageEmail()
                navController.navigate("verification/${email.value}/$otp")


            }
        }
    }


}

@Composable
fun MassageEmail(){

    AlertDialog(
        onDismissRequest = { true },
        containerColor = Color.White,
        modifier = Modifier.fillMaxWidth(),
        icon = {
            Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(100))
                .background(colorResource(R.color.accent)),
                contentAlignment = Alignment.Center) {
                Icon(
                    ImageBitmap.imageResource(R.drawable.email),
                    contentDescription = "Информация о приложении",
                    tint = colorResource(R.color.block),
                    modifier = Modifier.size(24.dp)

                )
            }
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
                textAlign = TextAlign.Center
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
