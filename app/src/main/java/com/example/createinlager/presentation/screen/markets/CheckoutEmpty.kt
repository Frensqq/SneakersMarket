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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.createinlager.R
import com.example.createinlager.presentation.MainActivity
import com.example.createinlager.presentation.theme.ui.cartSmallTitleStyle
import com.example.createinlager.presentation.theme.ui.cartSmallTitleStyleTwo
import com.example.createinlager.presentation.theme.ui.miniTextButton
import com.example.createinlager.presentation.theme.ui.nameTextField
import com.google.android.play.integrity.internal.b
import com.google.android.play.integrity.internal.m
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlin.jvm.java
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun CheckoutEmpty(){

    var stateScreenEmpty = remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxWidth()
        .height(413.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(colorResource(R.color.block)
    )) {

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {

            Text(stateScreenEmpty.value.toString() + "Контактная информация", style = cartSmallTitleStyle, color = colorResource(R.color.text))



                Spacer(modifier = Modifier.height(16.dp))
                stateScreenEmpty.value = rowCheckoutEmpty("Email", stateScreenEmpty.value)

                Spacer(modifier = Modifier.height(16.dp))
                stateScreenEmpty.value = rowCheckoutEmpty("Телефон", stateScreenEmpty.value)

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Адрес",
                            style = cartSmallTitleStyle,
                            color = colorResource(R.color.text)
                        )
                        Text(
                            "Тут будет адрес",
                            style = miniTextButton,
                            color = colorResource(R.color.hint)
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Icon(
                            bitmap = ImageBitmap.imageResource(R.drawable.edit),
                            tint = colorResource(R.color.hint),
                            contentDescription = null,
                            modifier = Modifier.size(15.dp).clickable(onClick = {stateScreenEmpty.value = !stateScreenEmpty.value})
                        )
                    }

                }



            Spacer(modifier = Modifier.height(16.dp))

            //Map
            val context = LocalContext.current

            Box(modifier = Modifier.fillMaxWidth()
                .height(101.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.disable)).clickable(onClick = {
                    context.startActivity(Intent(context, MainActivityTwo::class.java))}
                )
            )

            //

            Spacer(modifier = Modifier.height(12.dp))

            Text("Способ оплаты", style = cartSmallTitleStyle, color = colorResource(R.color.text))

            Spacer(modifier = Modifier.height(12.dp))

            RowCheckoutEmptyCard("Добавить")


        }


    }



}

@Composable
fun rowCheckoutEmpty(TextType: String, state: Boolean): Boolean{

    var stateScreen = state

    var textPrew =""
    var icon = R.drawable.edit

    if (TextType == "Email"){
        icon = R.drawable.email
        textPrew = "*******@****.***"
    }
    else{
        icon = R.drawable.phone
        textPrew = "**_***_***_****"
    }


    Row(modifier = Modifier.fillMaxWidth().height(40.dp)) {


        Box(modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.background)), contentAlignment = Alignment.Center
        ){
            Icon(bitmap = ImageBitmap.imageResource(icon),
                tint = colorResource(R.color.text), contentDescription = null, modifier = Modifier.size(20.dp))

        }

        Column(modifier = Modifier.padding(start = 12.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
            Text(textPrew, style = cartSmallTitleStyleTwo, color = colorResource(R.color.text), modifier = Modifier.height(20.dp))
            Text(TextType, style = miniTextButton, color = colorResource(R.color.hint))
        }

        Box(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            , contentAlignment = Alignment.CenterEnd
        ){
            Icon(bitmap = ImageBitmap.imageResource(R.drawable.edit),
                tint = colorResource(R.color.hint),
                contentDescription = null,
                modifier = Modifier.size(15.dp).clickable(onClick = {stateScreen = !stateScreen}))

        }
        return stateScreen
    }
    return stateScreen

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

