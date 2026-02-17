package com.example.createinlager.presentation.screen.markets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.data.ConverToArrayArray
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.viewModels.MarketViewModel
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TitleCategoryType

@Composable
fun Search(userId: String, token: String, navController: NavController, viewModel: MarketViewModel = viewModel()){

    var sneakers: Array<Array<String>> = emptyArray()

    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.background))) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                buttonBack(navController, "Home/${userId}/${token}")

                Text("Поиск", style = TitleCategoryType)

                Spacer(modifier = Modifier.size(44.dp))


            }

            Spacer(modifier = Modifier.height(26.dp))

            sneakers = MarketTextField("Поиск", 1f, true)

//            Spacer(modifier = Modifier.height(31.dp))
//
//            Row {
//
//                Icon(
//                    bitmap = ImageBitmap.imageResource(R.drawable.clock),
//                    contentDescription = null,
//                    modifier = Modifier.size(22.dp)
//                )
//                Text("", style = ButtonText, color = colorResource(R.color.text))
//
//            }
        }

        Spacer(modifier = Modifier.height(23.dp))


        if (sneakers.isNotEmpty()) {
            columnProducts(sneakers, userId, token, navController)
        }

    }

}