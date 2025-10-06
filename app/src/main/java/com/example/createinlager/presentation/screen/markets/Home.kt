package com.example.createinlager.presentation.screen.markets

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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.data.ConverToArrayArray
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.presentation.screen.nameTextField
import com.example.createinlager.presentation.screen.viewModels.MarketViewModel
import com.example.createinlager.presentation.theme.ui.TitleAuth
import com.example.createinlager.presentation.theme.ui.miniTextButton

@Composable
fun Home(userId: String, token:String, navController: NavController, viewModel: MarketViewModel = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }



    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.SneakersImport("id!='null'", "+created", 2)
            //ClassSnekers.value = viewModel.sneakersList.value
            isInitialized = true

        }
    }

    val ClassSnekers = viewModel.sneakers.collectAsState()

    val ListSneakers = ConverToArrayArray(ClassSnekers)


    Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.background))) {

        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                Spacer(modifier = Modifier.height(51.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton(onClick = {}) {
                        Box(modifier = Modifier.height(18.dp).width(26.dp)) {
                            Image(
                                bitmap = ImageBitmap.imageResource(R.drawable.burger),
                                contentScale = ContentScale.FillHeight,
                                contentDescription = null
                            )
                        }
                    }

                    Text("Главная", style = TitleAuth)

                    IconButton(onClick = {}, modifier = Modifier.size(44.dp)) {
                        Box(
                            modifier = Modifier.size(44.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(colorResource(R.color.background)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(R.drawable.bag),
                                modifier = Modifier.size(24.dp),
                                contentDescription = null
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.height(21.dp))

                Row(modifier = Modifier.fillMaxWidth()) {


                    MarketTextField("Поиск", 0.80f)


                    IconButton(
                        onClick = {},
                        modifier = Modifier.padding(start = 14.dp).size(52.dp)
                    ) {
                        Box(
                            modifier = Modifier.size(52.dp)
                                .clip(CircleShape)
                                .background(colorResource(R.color.accent)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(R.drawable.sliders),
                                modifier = Modifier.size(24.dp),
                                tint = colorResource(R.color.white),
                                contentDescription = null
                            )
                        }
                    }
                }

                nameTextField("Категории", 23, 15)

            }

            CategoryRow(token,userId,navController)

            Column(modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {

                Spacer(modifier = Modifier.height(23.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    nameTextField("Популярное", 0, 0)

                    Text("Все", style = miniTextButton, color = colorResource(R.color.accent))
                }

                Spacer(modifier = Modifier.height(34.dp))

            }

                if (ClassSnekers.value.isNotEmpty()) {
                    columnProducts(ListSneakers, userId)
                }
            Column(modifier = Modifier.padding(horizontal = 20.dp).fillMaxSize()) {


                Spacer(modifier = Modifier.height(29.dp))

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                    nameTextField("Акции", 0, 0)

                    Text("Все", modifier = Modifier.clickable(onClick = {}), style = miniTextButton, color = colorResource(R.color.accent))
                }

                Spacer(modifier = Modifier.height(19.dp))





            }

        }
    }



}
