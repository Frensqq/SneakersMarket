package com.example.createinlager.presentation.screen.markets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.cartSmallTitleStyle
import com.example.createinlager.presentation.theme.ui.cartSmallTitleStyleTwo
import com.example.createinlager.presentation.theme.ui.miniTextButton
import com.example.createinlager.presentation.theme.ui.nameTextField

@Composable
fun CheckoutEmpty(){

    Box(modifier = Modifier.fillMaxWidth()
        .height(413.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(colorResource(R.color.block)
    )) {

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {

            Text("Контактная информация", style = cartSmallTitleStyle, color = colorResource(R.color.text))

            Spacer(modifier = Modifier.height(16.dp))

            RowCheckoutEmpty("Email")

            Spacer(modifier = Modifier.height(16.dp))

            RowCheckoutEmpty("Телефон")

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth().height(44.dp)) {

                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                        Text("Адрес", style = cartSmallTitleStyle, color = colorResource(R.color.text))
                        Text("Тут будет адрес", style = miniTextButton, color = colorResource(R.color.hint))
                    }

            }

            Spacer(modifier = Modifier.height(16.dp))

            //Map

            Box(modifier = Modifier.fillMaxWidth()
                .height(101.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.disable)))

            //

            Spacer(modifier = Modifier.height(12.dp))

            Text("Способ оплаты", style = cartSmallTitleStyle, color = colorResource(R.color.text))

            Spacer(modifier = Modifier.height(12.dp))

            RowCheckoutEmptyCard("Добавить")


        }


    }



}

@Composable
fun RowCheckoutEmpty(TextType: String){

    var textPrew =""

    if (TextType == "Email"){
        textPrew = "*******@****.***"
    }
    else{
        textPrew = "**_***_***_****"
    }

    Row(modifier = Modifier.fillMaxWidth().height(40.dp)) {


        Box(modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.background)), contentAlignment = Alignment.Center
        ){
        }

        Column(modifier = Modifier.padding(start = 12.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
            Text(textPrew, style = cartSmallTitleStyleTwo, color = colorResource(R.color.text))
            Text(TextType, style = miniTextButton, color = colorResource(R.color.hint))
        }

        Box(modifier = Modifier
            .fillMaxHeight()
            , contentAlignment = Alignment.Center
        ){
        }

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

