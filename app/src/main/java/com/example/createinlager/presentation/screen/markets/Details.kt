package com.example.createinlager.presentation.screen.markets

import android.view.RoundedCorner
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.createinlager.R
import com.example.createinlager.data.ConverToArrayArray
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.viewModels.MarketViewModel
import com.example.createinlager.presentation.screen.viewModels.viewMarketCart
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TextFieldPlace
import com.example.createinlager.presentation.theme.ui.TextInfo
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleCategoryType
import com.example.createinlager.presentation.theme.ui.TitleDetails
import com.example.createinlager.presentation.theme.ui.TypeCostDetails

@Composable
fun SneakersDetails(sneakersId:String, userId: String, token:String, CurretidFavorites: String,CurretidInCarts: String, navController: NavController, viewModel: MarketViewModel = viewModel(), viewModelCart: viewMarketCart = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.SneakersImport( "id = '${sneakersId}'","+created",150)
            isInitialized = true
        }
    }

    val ClassSneakers = viewModel.sneakers.collectAsState()



    if (ClassSneakers.value.isNotEmpty()) {

        val listImage = ClassSneakers.value[0].image

        val sneakers = ConverToArrayArray(ClassSneakers)

        Column(
            modifier = Modifier.fillMaxSize().background(colorResource(R.color.background))
                .padding(top= 48.dp, start = 20.dp, end= 20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                buttonBack(navController, "Home/${userId}/${token}")

                Text("Sneaker Shop", style = TitleCategoryType)

                IconButton(onClick = {}, modifier = Modifier.size(44.dp)) {
                    Box(
                        modifier = Modifier.size(44.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(R.color.white)),
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

            Spacer(modifier =   Modifier.height(26.dp))

            Text(sneakers[0][3], style = TitleDetails, color = colorResource(R.color.text))

            var curretGender = ""

            if (sneakers[0][7]=="male"){
                curretGender="Men’s Shoes"
            }
            else{
                curretGender="Girl’s Shoes"
            }
            Text(curretGender , style = TextOnBoardTypeSmall ,modifier = Modifier.padding(top=5.dp), color = colorResource(R.color.hint))

            Text("₽" + sneakers[0][4], style = TypeCostDetails, modifier = Modifier.padding(top=5.dp))

            imageoutput(
                listImage,
                sneakers[0][0],
                        sneakers[0][2]

            )

            var MaxLines = remember { mutableStateOf(3) }
            Column() {

                Text(
                    sneakers[0][5],
                    style = TextInfo,
                    modifier = Modifier.padding(top = 33.dp).heightIn(max  = 150.dp).verticalScroll(rememberScrollState()),
                    maxLines = MaxLines.value,
                    color = colorResource(R.color.hint),
                    overflow = TextOverflow.Ellipsis,

                )

                Row(modifier = Modifier.padding(top = 10.dp).fillMaxWidth()) {
                    Text(
                        text = if (MaxLines.value ==3) "Подробнее" else "Свернуть",
                        modifier = Modifier.fillMaxWidth().clickable(onClick = {
                            if (MaxLines.value < 10) MaxLines.value = 30
                            else MaxLines.value = 3
                        }),
                        style = TextFieldPlace,
                        color = colorResource(R.color.accent),
                        textAlign = TextAlign.End
                    )

                }
            }


        }

        val listFavorite = listFavorite("(iduser = '$userId')&&(idsneakers = '${sneakersId}')")

        var CurretidInCart by remember { mutableStateOf(CurretidInCarts) }
        var inCart by remember { mutableStateOf(CurretidInCart.isNotEmpty()) }
        var CurretidFavorite = remember { mutableStateOf(CurretidFavorites) }
        var loved by remember { mutableStateOf(CurretidFavorite.value.isNotEmpty()) }


        Box(modifier = Modifier.padding(bottom = 40.dp).fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                IconButton(
                    onClick = {
                        if (loved){
                            viewModel.DeleteFavorite(CurretidFavorite.value)

                            loved = false
                        }
                        else{
                            viewModel.CreateFavorite(userId,sneakersId)

                            loved = true
                        }


                    },
                    modifier = Modifier.size(52.dp)
                        .clip(CircleShape)
                ) {

                    Box(modifier = Modifier.size(52.dp)
                        .clip(CircleShape)
                        .background(Color(0x66D9D9D9)), contentAlignment = Alignment.Center) {

                        Icon(
                            bitmap = ImageBitmap.imageResource(id = if (loved) R.drawable.heart else R.drawable.empty_heart),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null,
                            tint = if (loved) colorResource(R.color.red) else colorResource(R.color.hint)
                        )
                    }

                }


                    Button(onClick = {
                        if (!inCart) {
                            viewModelCart.addtocart(userId,sneakersId)
                            inCart = !inCart
                        } else {
                            viewModelCart.delCart(CurretidInCarts)
                            inCart = !inCart

                        }

                    },
                        modifier = Modifier.padding(start = 18.dp)
                            .height(50.dp)
                            .fillMaxSize(),
                        shape = RoundedCornerShape(13.dp),

                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = colorResource(R.color.disable),
                            containerColor = colorResource(R.color.accent),
                            contentColor = colorResource(R.color.block)
                        )
                    ){
                        Row(modifier = Modifier.padding(horizontal = 12.dp).fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically)
                        {

                            Icon(
                                bitmap = ImageBitmap.imageResource(R.drawable.bag),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp),
                            )

                            Text(
                                text = if (inCart) "Добавлено" else "В корзину",
                                style = ButtonText,
                                color = colorResource(R.color.block)
                            )


                            Spacer(modifier = Modifier.size(24.dp))
                        }
                    }





            }
        }


    }

}



@Composable
fun imageoutput(listImage: List<String>, collectionId:String, idSneak: String, viewModel: MarketViewModel = viewModel()){

    var selectedIndex by remember { mutableIntStateOf(0) }


    Column(modifier = Modifier.padding(top = 30.dp).heightIn(max = 350.dp)) {
        // Основное изображение
        if (listImage.isNotEmpty()) {
            Box(modifier = Modifier.heightIn(max = 226.dp)) {

                Column {
                    Box(modifier = Modifier.fillMaxSize(0.3f))

                    Box(
                        modifier = Modifier.fillMaxWidth().fillMaxSize(0.6f)
                            .background(colorResource(R.color.background)) // Белый фон контейнера
                    ) {
                        Canvas(
                            modifier = Modifier.matchParentSize()
                        ) {
                            drawOval(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0x00000000),Color(0xFF2363F6) ),
                                ),
                                topLeft = Offset.Zero,

                                style = Stroke(width = 2.dp.toPx())
                            )
                        }
                    }

                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)
                        .background(colorResource(R.color.background))
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(viewModel.getImage(collectionId, idSneak, listImage[selectedIndex]))
                        .size(Size.ORIGINAL)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 47.dp).fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                )
            }
        }

        Spacer(modifier = Modifier.height(37.dp))
        // Миниатюры
        if (listImage.isNotEmpty()) {
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

                var currentI = remember { mutableStateOf(-1) }

                for (i in 0..listImage.size-1) {

                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .clickable {selectedIndex = i
                                currentI.value = i

                            }.border(if (currentI.value == i) 3.dp else -1.dp, color = colorResource(R.color.accent), shape = RoundedCornerShape(16.dp))
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(viewModel.getImage(collectionId, idSneak, listImage[i]))
                                .size(Size.ORIGINAL)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(56.dp).clip(RoundedCornerShape(4.dp)).background(Color.White),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
        }
    }
}