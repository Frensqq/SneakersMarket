package com.example.createinlager.presentation.screen.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.domain.state.ResultState
import com.example.createinlager.presentation.screen.ErrorAunth
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.nameTextField
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleAuth

@Composable
fun Verification(email:String, otpId:String, navController: NavController, viewModel: UserViewModel = viewModel()){

    var currentOtpCode by remember { mutableStateOf(otpId) }

    Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.white))) {

        Column(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(61.dp))

            buttonBack(navController, "forgotPassword")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "OTP Проверка",
                style = TitleAuth,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.text),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения",
                style = TextOnBoardTypeSmall,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.hint),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            nameTextField("OTP Код", 0, 20)

            TextFieldOtp(currentOtpCode, navController)

            val temp = CountdownTimer(email)
            if (temp.isNotEmpty()) currentOtpCode = temp

        }
    }

}

@Composable
fun TextFieldOtp(otp: String,navController: NavController, viewModel: UserViewModel = viewModel()){

    var codes by remember { mutableStateOf(List(6) { "" }) }
    val focusManager = LocalFocusManager.current

    val result = viewModel.resultState.collectAsState()

    LazyRow{
        items(6) {index ->

            OutlinedTextField(
                value = codes[index],
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        val newCodes = codes.toMutableList()
                        newCodes[index] = newValue
                        codes = newCodes

                        if (newValue.isNotEmpty() && index < 5) {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    }
                },
                modifier = Modifier.padding(start = 15.dp)
                    .width(46.dp)
                    .height(99.dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(600)
                ),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor= colorResource(R.color.red),
                    unfocusedBorderColor = Color(0xffeaeaeb),
                    focusedContainerColor = Color(0xffeaeaeb),
                    unfocusedContainerColor = Color(0xffeaeaeb),
                    unfocusedTextColor = colorResource(R.color.text),
                    focusedTextColor = colorResource(R.color.text)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext ={if (index == 5) {
                    viewModel.sigInWithOtp(codes.joinToString(""),otp)
                } }),
                singleLine = true,
            )
        }
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

            val userData = viewModel.user.value
            val token = viewModel.token.value
            val otp = viewModel.otpCode.value



            if(userData != null){
                navController.navigate("CreateNewPassword/${userData.id}/${token}")
            }

            //MassageEmail()
        }
    }

}

@Composable
fun CountdownTimer(email: String, viewModel: UserViewModel = viewModel()): String {
    var timeLeft by remember { mutableStateOf(30) } // Начальное время - 30 секунд
    var isRunning by remember { mutableStateOf(true) }
    var otp by remember { mutableStateOf("") }

    otp = viewModel.otpCode.value

    LaunchedEffect(key1 = isRunning, key2 = timeLeft) {
        if (isRunning && timeLeft > 0) {
            kotlinx.coroutines.delay(
                1000
            )

            timeLeft-- // Уменьшаем оставшееся время
        } else if (timeLeft == 0) {
            isRunning = false // Таймер завершил отсчёт
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth(0.5f), contentAlignment = Alignment.TopStart) {

            if (!isRunning) {
                TextButton(
                    onClick = {
                        timeLeft = 30
                        isRunning = !isRunning
                        viewModel.sendOTPCode(email.toString())
                    },
                ) {
                    Text(
                        "Отправить заново",
                        fontSize = 12.sp,
                        color = colorResource(R.color.subtextdark)
                    )
                }

            }
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {

            if (isRunning) Text(
                text = if (timeLeft > 9) "00:" + timeLeft.toString() else "00:0" + timeLeft.toString(),
                fontSize = 12.sp,
                modifier = Modifier.padding(end = 20.dp),
                color = colorResource(
                    R.color.subtextdark
                )
            )
        }
    }
    return otp
}


