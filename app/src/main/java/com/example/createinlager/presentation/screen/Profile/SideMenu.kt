package com.example.createinlager.presentation.screen.Profile

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.ProfileName

@Composable
fun SideMenu(){

    Box(modifier = Modifier.fillMaxSize().background(Color(0xff48b2e7))) {

        Column(modifier = Modifier.padding(start=20.dp)) {

            Spacer(modifier = Modifier.height(78.dp))

            Box(modifier = Modifier.padding(start=16.dp).size(96.dp).clip(RoundedCornerShape(100)).background(colorResource(R.color.background)))

            Spacer(modifier = Modifier.height(15.dp))

            Text("Тестовое имя", style = ProfileName, color = colorResource(R.color.block), modifier = Modifier.padding(start=16.dp))
            Spacer(modifier = Modifier.height(57.dp))

            ProfileRow(R.drawable.people, "Профиль")
            Spacer(modifier = Modifier.height(30.dp))

            ProfileRow(R.drawable.cart, "Корзина")
            Spacer(modifier = Modifier.height(30.dp))

            ProfileRow(R.drawable.empty_heart, "Избранное")
            Spacer(modifier = Modifier.height(31.dp))

            ProfileRow(R.drawable.people, "Заказы")
            Spacer(modifier = Modifier.height(28.dp))

            ProfileRow(R.drawable.notification, "Уведомления")
            Spacer(modifier = Modifier.height(30.dp))

            ProfileRow(R.drawable.people, "Настройки")
            Spacer(modifier = Modifier.height(38.dp))

            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x3bf7f7f9)))
            Spacer(modifier = Modifier.height(30.dp))

            ProfileRow(R.drawable.people, "Выйти")

        }
    }
}

@Composable
fun ProfileRow(icon: Int, text: String){
    Row(modifier = Modifier.fillMaxWidth()) {

        Icon(bitmap = ImageBitmap.imageResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colorResource(R.color.block))

        Spacer(modifier = Modifier.width(27.dp))

        Text(text, style = ProfileName, color = colorResource(R.color.block))

    }
}

@Preview
@Composable
fun testSideMenu(){

    SideMenu()



}

