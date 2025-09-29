package com.example.createinlager.presentation.screen.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.domain.state.ResultState
import com.example.createinlager.presentation.screen.AccentLongButton
import com.example.createinlager.presentation.screen.ErrorAunth
import com.example.createinlager.presentation.screen.ErrorEmail
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.nameTextField
import com.example.createinlager.presentation.screen.passwordFieldAunth
import com.example.createinlager.presentation.screen.textFieldAunth
import com.example.createinlager.presentation.screen.validateEmail
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleAuth
import com.example.createinlager.presentation.theme.ui.bottomText
import com.example.createinlager.presentation.theme.ui.miniTextButton

@Composable
fun SingIn(navController: NavController, viewModel: UserViewModel = viewModel()){

    val result = viewModel.resultState.collectAsState()
    var email = remember { mutableStateOf("") }
    var openDialog = remember { mutableStateOf(false) }
    var openDialogTwo = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.white))) {


        Column(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(66.dp))

            buttonBack(navController, "RegisterAcc")

            Spacer(modifier = Modifier.height(11.dp))

            Text(
                "Привет!",
                style = TitleAuth,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.text),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Заполните Свои данные",
                style = TextOnBoardTypeSmall,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.hint),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(54.dp))

            nameTextField("Email", 0, 12)

            email = textFieldAunth("xyz@gmail.com", true, 0)

            nameTextField("Пароль", 30, 12)

            val password = passwordFieldAunth("xxxxxxxx")

            Text(
                "Востановить",
                textAlign = TextAlign.End,
                modifier = Modifier.padding(top = 12.dp).fillMaxWidth()
                    .clickable { navController.navigate("forgotPassword") },
                style = miniTextButton
            )

            Spacer(modifier = Modifier.height(24.dp))




            AccentLongButton({if (email.value.isNotEmpty() and password.value.isNotEmpty())
            {if (validateEmail(email.value))
            {viewModel.SingIn(email.value, password.value)}
            else openDialog.value = true}
            else openDialogTwo.value = true}, "Войти", true)
            Box(
                modifier = Modifier.padding( bottom = 47.dp).fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {

                Row {
                    Text("Вы впервые?", style = bottomText, color = colorResource(R.color.hint))

                    Text(
                        " Создать",
                        style = bottomText,
                        color = colorResource(R.color.text),
                        modifier = Modifier.clickable { navController.navigate("RegisterAcc") })
                }
            }
        }

    }

    if (openDialog.value) {
        openDialog.value = ErrorEmail(openDialog.value, "Введен некоректный email", "попробуйте ввести ещё раз")
    }
    if (openDialogTwo.value) {
        openDialogTwo.value = ErrorEmail(openDialogTwo.value, "Необходимо заполнить все поля","Пожалуйства заполните все поля" )
    }


    when(result.value){
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
        is ResultState.Error -> {
            ErrorAunth((result.value as ResultState.Error).message, "Ошибка авторизации!")

        }
        is ResultState.Success -> {

            navController.navigate("SingIn")



        }


    }








}
