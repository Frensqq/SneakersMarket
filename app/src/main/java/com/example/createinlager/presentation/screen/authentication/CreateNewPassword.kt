package com.example.createinlager.presentation.screen.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.domain.state.ResultState
import com.example.createinlager.presentation.screen.AccentLongButton
import com.example.createinlager.presentation.screen.ErrorAunth
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.nameTextField
import com.example.createinlager.presentation.screen.passwordFieldAunth
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleAuth

@Composable
fun CreateNewPassword(userId: String,token: String, navController: NavController, viewModel: UserViewModel = viewModel()){

    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.white)).padding(horizontal = 20.dp)) {

        Spacer(modifier = Modifier.height(61.dp))

        buttonBack(navController, "forgotPassword")

        Spacer(modifier = Modifier.height(19.dp))

        Text(
            "Задать Новый Пароль",
            style = TitleAuth,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Установите Новый Пароль Для Входа В Вашу Учетную Запись",
            style = TextOnBoardTypeSmall,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.hint),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(38.dp))

        nameTextField("Старый пароль", 0, 12)

        val oldPassword = passwordFieldAunth("*******")

        nameTextField("Пароль", 20, 12)

        val password = passwordFieldAunth("*******")

        nameTextField("Подтверждение пароля", 20, 12)

        val confirmPassword = passwordFieldAunth("*******")

        Spacer(modifier = Modifier.height(40.dp))

        AccentLongButton({
            viewModel.passwordUpdate(
                oldPassword.value,
                token,
                password.value,
                confirmPassword.value,
                userId
            )
        }, "Сохранить", true)
    }

    val result = viewModel.resultState.collectAsState()

    when (result.value) {
        is ResultState.Error -> {
            ErrorAunth((result.value as ResultState.Error).message, "Ошибка смены пароля")
        }
        ResultState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center

            ) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(top= 400.dp), strokeWidth = 10.dp, color = colorResource(R.color.accent))
            }
        }
        ResultState.Initialized -> {
        }
        is ResultState.Success -> {

            navController.navigate("SingIn")

        }
    }

}