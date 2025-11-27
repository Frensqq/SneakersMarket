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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.createinlager.data.ListFavoriteSneakersCreate
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.viewModels.MarketViewModel
import com.example.createinlager.presentation.theme.ui.TitleCategoryType

@Composable
fun Favorites(userId: String,token:String,navController: NavController, viewModel: MarketViewModel = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.SneakersImport("id != 'null'",  "+created", 150)
            isInitialized = true
        }
    }

    val ClassSnekers = viewModel.sneakers.collectAsState()

    val ListSneakers = ConverToArrayArray(ClassSnekers)
    val listFavorite = listFavorite("(iduser = '$userId')")
    val ListFavoriteSneakers = ListFavoriteSneakersCreate(ListSneakers,listFavorite)


    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.background))) {

        Spacer(modifier = Modifier.padding(top= 48.dp))


        Row(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            buttonBack(navController, "Home/${userId}/${token}")

            Text("Избранное", style = TitleCategoryType)

            IconButton(onClick = {}, modifier = Modifier.size(44.dp)) {
                Box(
                    modifier = Modifier.size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colorResource(R.color.white)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        bitmap = ImageBitmap.imageResource(R.drawable.heart),
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(R.color.red),
                        contentDescription = null
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        if (ListSneakers.isNotEmpty()) {
            columnProducts(ListFavoriteSneakers, userId, token, navController)
        }

    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        navigationBar(userId, token, 1,navController)
    }


}