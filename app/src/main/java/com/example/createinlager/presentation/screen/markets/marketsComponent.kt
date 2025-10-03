package com.example.createinlager.presentation.screen.markets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.textCategory
import com.example.createinlager.presentation.theme.ui.textInFiedMarket

@Composable
fun MarketTextField(text: String, fraction: Float) {

    val string = remember { mutableStateOf("") }

    OutlinedTextField(
        value = string.value,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(fraction).height(52.dp)
            .shadow(elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)),
        textStyle = textInFiedMarket,
        leadingIcon = {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.search),
                tint = colorResource(R.color.hint),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        placeholder = { Text(text, style = textInFiedMarket) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.accent),
            unfocusedBorderColor = colorResource(R.color.darkWhite),
            focusedContainerColor = colorResource(R.color.white),
            unfocusedContainerColor = colorResource(R.color.white),
            unfocusedTextColor = colorResource(R.color.hint),
            focusedTextColor = colorResource(R.color.text)

        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        )

}

@Composable
fun CategoryRow(){

    val category = listOf("Все", "Outdoor", "Tennis", "Running")

    LazyRow() {

        items(category.size) {index ->

            Button(

                onClick = { },
                modifier = Modifier.padding(start = 20.dp ,end = if(index == category.size-1){20.dp} else 0.dp).height(40.dp).width(108.dp).shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.white),
                ),
            ) {
                Text(
                    category[index],
                    style = textCategory,
                    color = colorResource(R.color.hint),
                    textAlign = TextAlign.Center
                )
            }

        }

    }

}


@Preview
@Composable
fun testComponents(){

    //MarketTextField("Поиск",1f)

    CategoryRow()

}