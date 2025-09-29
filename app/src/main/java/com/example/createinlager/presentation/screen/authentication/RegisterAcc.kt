package com.example.createinlager.presentation.screen.authentication

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
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
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TextUnderLine
import com.example.createinlager.presentation.theme.ui.TitleAuth
import com.example.createinlager.presentation.theme.ui.bottomText


@Composable
fun RegisterAcc(navController: NavController, viewModel: UserViewModel = viewModel()){

    val result = viewModel.resultState.collectAsState()
    var openDialog = remember { mutableStateOf(false) }
    var checkBox by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize().background(colorResource(R.color.white))){
        Column(modifier = Modifier.padding(horizontal = 20.dp).fillMaxSize()) {

            Spacer(modifier = Modifier.height(63.dp))

            buttonBack(navController, "OnBoards")

            Spacer(modifier = Modifier.height(11.dp))

            Text("Регистрация", style = TitleAuth, textAlign = TextAlign.Center, color = colorResource(R.color.text), modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))

            Text("Заполните Свои данные", style = TextOnBoardTypeSmall,textAlign = TextAlign.Center, color = colorResource(R.color.hint), modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(54.dp))

            nameTextField("Ваше Имя", 0, 12)

            val name = textFieldAunth("xxxxxxxxx", false, 2)

            nameTextField("Email", 12, 12)

            val email = textFieldAunth("xyz@gmail.com", true, 0)

            nameTextField("Пароль", 12, 12)

            val password = passwordFieldAunth("*******")

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                Box(modifier = Modifier.size(18.dp).clip(RoundedCornerShape(6.dp)).clickable(onClick ={checkBox = !checkBox} )
                    .background(colorResource(
                        if (!checkBox){(R.color.background)}
                        else {(R.color.accent)})), contentAlignment = Alignment.Center)
                {
                    Icon(bitmap = ImageBitmap.imageResource(R.drawable.shield), contentDescription = null, modifier = Modifier.size(10.dp))

                }

                val context = LocalContext.current
                val intent = remember { Intent(Intent.ACTION_VIEW, "https://github.com/Frensqq".toUri()) }

                Spacer(modifier = Modifier.width(12.dp))

                Text("Даю согласие на обработку\n персональных данных", style = TextUnderLine , color = colorResource(R.color.hint),
                    modifier = Modifier.clickable { context.startActivity(intent) }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            AccentLongButton({ if (validateEmail(email.value)) {viewModel.Registration(name.value,email.value,password.value)} else openDialog.value = true}, "Зарегистрироваться",checkBox)

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){

                Row(modifier = Modifier.fillMaxSize().padding(bottom = 47.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
                    Text("Есть аккаунт?", style = bottomText, color = colorResource(R.color.hint))

                    Text(" Войти", style = bottomText, color = colorResource(R.color.text), modifier = Modifier.clickable { navController.navigate("SingIn") })
                }
            }
        }

        if (openDialog.value) {
            openDialog.value = ErrorEmail(openDialog.value,"Введен некоректный email", "попробуйте ввести ещё раз")
        }

        when (result.value) {
            is ResultState.Error -> {
                ErrorAunth((result.value as ResultState.Error).message, "Ошибка Регистрации")
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

                navController.navigate("SingIn")

            }
        }
    }
}