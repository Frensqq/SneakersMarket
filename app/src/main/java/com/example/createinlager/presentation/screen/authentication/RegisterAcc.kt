package com.example.createinlager.presentation.screen.authentication

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TextFieldPlace
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TextUnderLine
import com.example.createinlager.presentation.theme.ui.TitleAuth
import com.example.createinlager.presentation.theme.ui.bottomText
import com.example.createinlager.presentation.theme.ui.nameTextField


@Composable
fun RegisterAcc(navController: NavController){

    var checkBox by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize().background(colorResource(R.color.white))){
        Column(modifier = Modifier.padding(horizontal = 20.dp).fillMaxSize()) {

            Spacer(modifier = Modifier.height(63.dp))

            buttonBack(navController,"SingIn")

            Spacer(modifier = Modifier.height(11.dp))

            Text("Регистрация", style = TitleAuth, textAlign = TextAlign.Center, color = colorResource(R.color.text), modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))

            Text("Заполните Свои данные", style = TextOnBoardTypeSmall,textAlign = TextAlign.Center, color = colorResource(R.color.hint), modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(54.dp))

            nameTextField("Ваше Имя",0,12)

            val name = textFieldAunth("xxxxxxxxx", false, 2)

            nameTextField("Email",12,12)

            val email = textFieldAunth("xyz@gmail.com", true, 0)

            nameTextField("Пароль",12,12)

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
            Spacer(modifier = Modifier.width(24.dp))

            Button(onClick = { },
                enabled = checkBox,
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
                Text("Зарегистрироваться", style = ButtonText, color = colorResource(R.color.block))
            }


            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){

                Row(modifier = Modifier.fillMaxSize().padding(bottom = 47.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
                    Text("Есть аккаунт?", style = bottomText, color = colorResource(R.color.hint))

                    Text(" Войти", style = bottomText, color = colorResource(R.color.text), modifier = Modifier.clickable {  })
                }
            }
        }
    }
}



@Composable
fun buttonBack(navController: NavController,Road: String){
    Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(40.dp)).background(colorResource(R.color.hint)), contentAlignment = Alignment.CenterStart) {
        IconButton(
            onClick = { navController.navigate(Road) },
            modifier = Modifier
                .clip(CircleShape)
                .background(colorResource(R.color.background))
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.back),
                contentDescription = "Назад",
                modifier = Modifier.fillMaxWidth(),
                tint = colorResource(R.color.text)
            )
        }
    }
}


@Composable
fun textFieldAunth(prew: String, type: Boolean, Spacing:Int): MutableState<String> {

    val text = remember{mutableStateOf("")}

    OutlinedTextField(
        text.value,
        {text.value = it},
        textStyle = TextStyle(fontSize =  14.sp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor= colorResource(R.color.accent),
            unfocusedBorderColor = colorResource(R.color.darkWhite) ,
            focusedContainerColor = colorResource(R.color.background),
            unfocusedContainerColor = colorResource(R.color.background),
            unfocusedTextColor = colorResource(R.color.hint),
            focusedTextColor = colorResource(R.color.text)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = if (type){KeyboardType.Email} else {
            KeyboardType.Text}),
        placeholder = { Box {Text(prew, style = TextFieldPlace, letterSpacing = Spacing.sp) } },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(top = 12.dp).height(50.dp).fillMaxWidth(),)

    return text
}

@Composable
fun passwordFieldAunth(prew: String): MutableState<String> {

    val password = remember{mutableStateOf("")}
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
            focusedBorderColor = colorResource(R.color.accent),
            unfocusedBorderColor = colorResource(R.color.darkWhite),
            focusedContainerColor = colorResource(R.color.background),
            unfocusedContainerColor = colorResource(R.color.background),
            unfocusedTextColor = colorResource(R.color.hint),
            focusedTextColor = colorResource(R.color.text)

        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            for(i in 0..8){
                Box(modifier = Modifier.padding(start = 3.dp).size(6.dp).clip(RoundedCornerShape(3.dp)).background(colorResource(R.color.hint))) {  }
            }
        } },
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

@Composable
fun nameTextField(text: String, topdp:Int,bottomdp:Int){

    Text(text, style = nameTextField, textAlign = TextAlign.Start,color = colorResource(R.color.text), modifier = Modifier.padding(top = topdp.dp, bottom = bottomdp.dp))

}