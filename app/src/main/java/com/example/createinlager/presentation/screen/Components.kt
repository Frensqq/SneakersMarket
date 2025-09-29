package com.example.createinlager.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TextFieldPlace
import com.example.createinlager.presentation.theme.ui.nameTextField
import kotlinx.coroutines.launch
import kotlin.collections.map

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
        placeholder = { Box {Text(prew, style = TextFieldPlace, letterSpacing = Spacing.sp, color =  colorResource(R.color.hint))} },
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.height(50.dp).fillMaxWidth(),)

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
        modifier = Modifier.height(50.dp)
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
fun nameTextField(text: String, topdp:Int,bottomdp:Int){

    Text(text, style = nameTextField, textAlign = TextAlign.Start,color = colorResource(R.color.text), modifier = Modifier.padding(top = topdp.dp, bottom = bottomdp.dp))

}

@Composable
fun ErrorAunth(RegError: String, TypeErroe: String){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = TypeErroe+"\n"+RegError,
                duration = SnackbarDuration.Long
            )
        }
    }


    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                containerColor = colorResource(R.color.accent),
                contentColor = Color.White,
                content = { Text(data.visuals.message, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                shape = RoundedCornerShape(13.dp),
            )
        }
    )
}

@Composable
fun WhiteButton(click:() -> Unit, text: String){
    Box(
        modifier = Modifier.padding(top = 100.dp, bottom = 36.dp, start = 20.dp, end = 20.dp)
            .fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {

        Button(
            onClick = click,
            modifier = Modifier.height(50.dp).fillMaxWidth(),
            shape = RoundedCornerShape(13.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.block),
            )
        ) {
            Text(text, style = ButtonText, color = colorResource(R.color.text))
        }
    }
}

@Composable
fun AccentLongButton(click:() -> Unit, text: String, state: Boolean){
    Button(onClick = click,
        enabled = state,
        modifier = Modifier
            .height(50.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(13.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = colorResource(R.color.disable),
            containerColor = colorResource(R.color.accent),
            contentColor = colorResource(R.color.block)
        )
    ){
        Text(text, style = ButtonText, color = colorResource(R.color.block))
    }
}

fun validateEmail(email: String): Boolean{

    val validEmail = "^[a-z0-9]+(\\.[a-z0-9]+)*@[a-z0-9]+\\.[a-z]{2,}$".toRegex()

    return email.matches(validEmail)
}

@Composable
fun ErrorEmail(open: Boolean, Tilte: String, text: String): Boolean{

    var openDialog = remember { mutableStateOf(open) }
    AlertDialog(
        onDismissRequest = { openDialog.value= false},
        title = { Text(text = Tilte) },
        text = { Text(text) },
        confirmButton = {
            Button({ openDialog.value = false }) {
                Text("OK", fontSize = 22.sp)
            }
        }
    )

    return openDialog.value
}


