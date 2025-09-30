package com.example.createinlager.presentation.screen.markets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.createinlager.R
import com.example.createinlager.presentation.theme.ui.textInFiedMarket

@Composable
fun MarketTextField(text: String) {

    val string = remember { mutableStateOf("") }

    OutlinedTextField(
        value = string.value,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        textStyle = textInFiedMarket,
        leadingIcon = {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.search),
                contentDescription = null
            )
        },
        placeholder = { Text(text, style = textInFiedMarket) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.accent),
            unfocusedBorderColor = colorResource(R.color.darkWhite),
            focusedContainerColor = colorResource(R.color.background),
            unfocusedContainerColor = colorResource(R.color.background),
            unfocusedTextColor = colorResource(R.color.hint),
            focusedTextColor = colorResource(R.color.text)

        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        )

}

@Preview
@Composable
fun testComponents(){

    MarketTextField("Поиск")

}