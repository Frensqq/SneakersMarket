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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.TextFieldPlace


@Composable
fun RegisterAcc(navController: NavController){
    Box(Modifier.fillMaxSize().background(colorResource(R.color.background))){
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            Spacer(modifier = Modifier.height(63.dp))

            val login = textFieldAunth("xxxxxxxxx")


        }
    }
}


@Composable
fun textFieldAunth(prew: String): MutableState<String> {

    val text = remember{mutableStateOf("")}

    OutlinedTextField(
        text.value,
        {text.value = it},
        textStyle = TextStyle(fontSize =  14.sp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor= Color(0xff48B2E7),
            unfocusedBorderColor = Color.White ,
            focusedContainerColor = Color(0xffeaeaeb),
            unfocusedContainerColor = Color(0xffeaeaeb),
            unfocusedTextColor = colorResource(R.color.hint),
            focusedTextColor = colorResource(R.color.text)
        ),
        placeholder = { Box {Text(prew, style = TextFieldPlace)  } },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(top = 12.dp).height(50.dp).fillMaxWidth(),)

    return text
}

@Composable
fun passwordFieldAunth(prew: String): MutableState<String> {

    val password = remember{mutableStateOf(prew)}
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    val IconIf: Int
    if (passwordVisibility){
        IconIf  =R.drawable.eyeopen }
    else {
        IconIf = R.drawable.eyeclose
    }

    OutlinedTextField(
        password.value, { password.value = it },
        textStyle = TextStyle(fontSize = 14.sp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xff48B2E7),
            unfocusedBorderColor = Color.White,
            focusedContainerColor = Color(0xffeaeaeb),
            unfocusedContainerColor = Color(0xffeaeaeb),
            unfocusedTextColor = colorResource(R.color.hint),
            focusedTextColor = colorResource(R.color.text)

        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(top = 12.dp).height(50.dp)
            .fillMaxWidth(),


        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility })
            {
                Icon(
                    ImageBitmap.imageResource(IconIf),
                    contentDescription = "Скрытие пароля",
                    modifier = Modifier.fillMaxSize(0.5f),
                    tint = if (passwordVisibility) Color(0xFF48B2E7) else Color(0xff6A6A6A)
                )
            }
        }
    )
    return password
}