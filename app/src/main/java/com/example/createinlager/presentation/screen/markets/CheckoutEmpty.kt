package com.example.createinlager.presentation.screen.markets

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.cartSmallTitleStyle
import com.example.createinlager.presentation.theme.ui.cartSmallTitleStyleTwo
import com.example.createinlager.presentation.theme.ui.miniTextButton
import kotlin.jvm.java
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.theme.ui.TypeIntCart
import com.example.createinlager.presentation.theme.ui.bottomText
import com.example.createinlager.presentation.theme.ui.checkouttype
import com.example.createinlager.presentation.theme.ui.mapText
import com.example.createinlager.presentation.theme.ui.textInFiedMarket

@Composable
fun CheckoutEmpty(userList: Array<String>, userId: String, token: String, viewModel: UserViewModel = viewModel()){

    var stateScreenEmpty by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf(userList[3]) }
    var phone by remember { mutableStateOf(userList[4]) }
    var address by remember { mutableStateOf(userList[5]) }

    Box(modifier = Modifier.fillMaxWidth()
        .height(if(stateScreenEmpty) 413.dp else 480.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(colorResource(R.color.block)
    )) {

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {

            Text( "Контактная информация", style = cartSmallTitleStyle, color = colorResource(R.color.text))
            Spacer(modifier = Modifier.height(16.dp))

                if (stateScreenEmpty) {

                    RowCheckoutEmpty(userList,"Email", { stateScreenEmpty = !stateScreenEmpty })

                    Spacer(modifier = Modifier.height(16.dp))
                    RowCheckoutEmpty(userList,"Телефон", { stateScreenEmpty = !stateScreenEmpty })
                }
                else{

                    RowCheckoutEmptyEdit(userList, R.drawable.mail,
                        { stateScreenEmpty = !stateScreenEmpty
                            viewModel.UpdateUser(userId, token, email, phone, "", address)
                        }, "Email",
                        onTextChange = { text->
                            email = text
                        })

                    Spacer(modifier = Modifier.height(16.dp))
                    RowCheckoutEmptyEdit(userList,R.drawable.phone,
                        { stateScreenEmpty = !stateScreenEmpty
                            viewModel.UpdateUser(userId, token, email, phone, "", address)}, "Phone",
                        onTextChange = { text->
                            phone = text
                        })

                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Адрес",
                            style = cartSmallTitleStyle,
                            color = colorResource(R.color.text)
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.height(if (stateScreenEmpty) 20.dp else 52.dp).fillMaxWidth()) {
                            if (stateScreenEmpty){
                            Text(
                                address,
                                style = miniTextButton,
                                color = colorResource(R.color.hint)
                            )
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Icon(
                                    bitmap = ImageBitmap.imageResource(R.drawable.edit),
                                    tint = colorResource(R.color.hint),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp).clickable(onClick = {
                                        stateScreenEmpty = !stateScreenEmpty
                                        viewModel.UpdateUser(userId, token, email, phone, "", address)
                                    })
                                )
                            }


                            }
                            else{
                                RowCheckoutEmptyEdit(userList,R.drawable.home,
                                    { stateScreenEmpty = !stateScreenEmpty
                                        viewModel.UpdateUser(userId, token, email, phone, "", address)}, "",
                                    onTextChange = { text->
                                        address = text
                                    })
                            }
                        }
                    }
                }
            Spacer(modifier = Modifier.height(16.dp))
            //Text(phone + "" + email)
            //Map
            val context = LocalContext.current

            Box(modifier = Modifier.fillMaxWidth()
                .height(101.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.disable)).clickable(onClick = {
                    context.startActivity(Intent(context, MainActivityTwo::class.java))}
                )
            ){
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                    Text("Посмотреть на карте", style = mapText, color = colorResource(R.color.block), textAlign = TextAlign.Center)

                    Box(modifier = Modifier.size(36.dp)
                        .clip(RoundedCornerShape(100))
                        .background(colorResource(R.color.accent)),
                        contentAlignment = Alignment.Center){

                        Icon(bitmap = ImageBitmap.imageResource(R.drawable.marker),
                            contentDescription = null,
                            tint = colorResource(R.color.block),
                            modifier = Modifier.size(20.dp))
                    }
                }
            }

            //

            Spacer(modifier = Modifier.height(12.dp))

            Text("Способ оплаты", style = cartSmallTitleStyle, color = colorResource(R.color.text))

            Spacer(modifier = Modifier.height(12.dp))

            RowCheckoutEmptyCard("Добавить")

        }
    }
}

@Composable
fun RowCheckoutEmpty(userList: Array<String>, TextType: String, onClick: () -> Unit) {

    var textPrew = ""
    var icon = R.drawable.edit


    if (TextType == "Email") {
        icon = R.drawable.email
        if (userList[3] == ""){
            textPrew = "*******@****.***"
        }
        else {
            textPrew = userList[3]

        }
    } else {
        icon = R.drawable.phone
        if (userList[4] == ""){
            textPrew = "**_***_***_****"
        }
        else {
            textPrew = userList[4]
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(colorResource(R.color.background)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(icon),
                tint = colorResource(R.color.text),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                textPrew,
                style = cartSmallTitleStyleTwo,
                color = colorResource(R.color.text),
                modifier = Modifier.height(20.dp)
            )
            Text(
                TextType,
                style = miniTextButton,
                color = colorResource(R.color.hint)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.edit),
                tint = colorResource(R.color.hint),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .clickable(onClick = onClick)
            )
        }
    }
}

@Composable
fun RowCheckoutEmptyEdit(userList: Array<String>,leadIcon: Int, onClick: () -> Unit, TextType: String,  onTextChange: (String) -> Unit) {

    var textPrew = ""
    var icon = R.drawable.edit

    if (TextType == "Email") {
        icon = R.drawable.email
        if (userList[3].isEmpty()){
            textPrew = "*******@****.***"
        }
        else {
            textPrew = userList[3]
        }

    } else if(TextType == "Phone"){
        icon = R.drawable.phone
        if (userList[4].isEmpty()){
            textPrew = "**_***_***_****"
        }
        else {
            textPrew = userList[4]
        }
    }
    else{
        textPrew = ""
    }


    val string = remember { mutableStateOf("") }


    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        OutlinedTextField(
            value = string.value,
            onValueChange = { string.value = it },
            modifier = Modifier.fillMaxWidth(0.9f).height(52.dp),
            textStyle = TypeIntCart,
            leadingIcon = {
                Icon(
                    bitmap = ImageBitmap.imageResource(leadIcon),
                    tint = colorResource(R.color.text),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = { Text(textPrew, style = textInFiedMarket) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.accent),
                unfocusedBorderColor = colorResource(R.color.background),
                focusedContainerColor = colorResource(R.color.background),
                unfocusedContainerColor = colorResource(R.color.background),
                unfocusedTextColor = colorResource(R.color.text),
                focusedTextColor = colorResource(R.color.text),
                disabledContainerColor = colorResource(R.color.white)

            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {

            }
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth().height(52.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.edit),
                tint = colorResource(R.color.hint),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .clickable(onClick = onClick)
            )
        }

    }

    if (string.value.isNotEmpty()) {
        onTextChange(string.value)
    }
}

@Composable
fun RowCheckoutEmptyCard(TextType: String){

    var textPrew ="**** ****"

    Row(modifier = Modifier.fillMaxWidth().height(40.dp)) {


        Box(modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.background)), contentAlignment = Alignment.Center
        ){
            Image(bitmap = ImageBitmap.imageResource(R.drawable.cardicon),  contentDescription = null, modifier = Modifier.size(32.dp), contentScale = ContentScale.FillWidth)
        }

        Column(modifier = Modifier.padding(start = 12.dp).fillMaxHeight()) {
            Text(TextType, style = cartSmallTitleStyleTwo, color = colorResource(R.color.text))
            Spacer(modifier = Modifier.height(4.dp))
            Text(textPrew, style = miniTextButton, color = colorResource(R.color.hint))
        }

        Box(modifier = Modifier
            .fillMaxHeight()
            , contentAlignment = Alignment.Center
        ){
        }

    }
}



@Preview
@Composable
fun ChechoutTest(){

}