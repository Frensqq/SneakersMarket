package com.example.createinlager.presentation.screen.OnBoards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TextOnBoardType
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleType

@Composable
fun OnBoards(navController: NavController) {

    var status by remember{ mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (status == 0) {
            Spacer(modifier = Modifier.heightIn(70.dp))

            Text("ДОБРО\nПОЖАЛОВАТЬ", style = TitleType, color = colorResource(R.color.block),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.heightIn(122.dp))
        } else Spacer(modifier = Modifier.heightIn(80.dp))

        val CurrentImage = if (status == 0) {
            R.drawable.image_1
        } else if (status == 1) {
            R.drawable.image_2
        } else {
            R.drawable.image_3
        }

        Image(
            bitmap = ImageBitmap.imageResource(CurrentImage),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        if (status == 1) {
            Spacer(modifier = Modifier.height(60.dp))

            Text("Начнем путешествие", style = TextOnBoardType, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = colorResource(R.color.block))
            Spacer(modifier = Modifier.height(13.dp))
            Text("Умная, великолепная и модная коллекция Изучите сейчас", style =TextOnBoardTypeSmall ,modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth(), textAlign = TextAlign.Center, color = colorResource(R.color.block))
            Spacer(modifier = Modifier.height(40.dp))

        }

        else if (status == 0) Spacer(modifier = Modifier.heightIn(26.dp))

        else {
            Spacer(modifier = Modifier.height(60.dp))

            Text("У вас есть сила, чтобы", style = TextOnBoardType, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = colorResource(R.color.block))
            Spacer(modifier = Modifier.height(13.dp))
            Text("В вашей комнате много красивых и привлекательных растений", style =TextOnBoardTypeSmall ,modifier = Modifier.padding(horizontal = 30.dp).fillMaxWidth(), textAlign = TextAlign.Center, color = colorResource(R.color.block))

            Spacer(modifier = Modifier.height(40.dp))
        }


        Row {
            if (status == 0) {
                Box(
                    modifier = (Modifier.width(43.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(colorResource(R.color.block)))
                )
            } else {
                Box(
                    modifier = Modifier.width(28.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(colorResource(R.color.disable))
                )
            }
            if (status == 1) {
                Box(
                    modifier = (Modifier.padding(start = 12.dp).width(43.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(colorResource(R.color.block)))
                )
            } else {
                Box(
                    modifier = Modifier.padding(start = 12.dp).width(28.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(colorResource(R.color.disable))
                )
            }
            if (status == 2) {
                Box(
                    modifier = (Modifier.padding(start = 12.dp).width(43.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(colorResource(R.color.block)))
                )
            } else {
                Box(
                    modifier = Modifier.padding(start = 12.dp).width(28.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(colorResource(R.color.disable))
                )
            }
        }

        Box(
            modifier = Modifier.padding(top = 100.dp, bottom = 36.dp, start = 20.dp, end = 20.dp)
                .fillMaxSize(), contentAlignment = Alignment.BottomCenter
        ) {

            Button(
                onClick = {if (status>=2) navController.navigate("RegisterAcc")
                    if (status!=2)status = status + 1},
                modifier = Modifier.height(50.dp).fillMaxWidth(),
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.block),
                )
            ) {
                Text("Начать", style = ButtonText, color = colorResource(R.color.text))
            }
        }
    }


    }
