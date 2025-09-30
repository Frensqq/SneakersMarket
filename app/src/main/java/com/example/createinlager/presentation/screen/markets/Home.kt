package com.example.createinlager.presentation.screen.markets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.TitleAuth

@Composable
fun Home(userId: String, token:String, navController: NavController){

    Box(        modifier = Modifier.fillMaxSize().background(colorResource(R.color.block))) {
        Column(modifier = Modifier.padding(horizontal = 20.dp).fillMaxSize()) {

            Spacer(modifier = Modifier.height(51.dp))

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                IconButton(onClick ={} ) {
                    Box(modifier = Modifier.height(18.dp).width(26.dp)) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.burger),
                            contentDescription = null
                        )
                    }
                }

                Text("Главная", style = TitleAuth)

                IconButton(onClick ={} ) {
                    Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)).background(colorResource(R.color.background)), contentAlignment = Alignment.Center) {
                        Icon(
                            bitmap = ImageBitmap.imageResource(R.drawable.bag),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                }

            }

            Row {

                Box(){
                MarketTextField("Поиск")
                }

                IconButton(onClick ={} ) {
                    Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)).background(colorResource(R.color.accent)), contentAlignment = Alignment.Center) {
                        Icon(
                            bitmap = ImageBitmap.imageResource(R.drawable.bag),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                }


            }


        }
    }



}
