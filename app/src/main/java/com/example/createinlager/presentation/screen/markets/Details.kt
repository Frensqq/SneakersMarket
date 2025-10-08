package com.example.createinlager.presentation.screen.markets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
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
import com.example.createinlager.presentation.theme.ui.TextFieldPlace
import com.example.createinlager.presentation.theme.ui.TextInfo
import com.example.createinlager.presentation.theme.ui.TextOnBoardTypeSmall
import com.example.createinlager.presentation.theme.ui.TitleAuth
import com.example.createinlager.presentation.theme.ui.TitleCategoryType
import com.example.createinlager.presentation.theme.ui.TitleDetails
import com.example.createinlager.presentation.theme.ui.TypeCostDetails
import kotlin.collections.get

@Composable
fun SneakersDetails(sneakersId:String, userId: String, token:String,  navController: NavController, viewModel: MarketViewModel = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.SneakersImport( "id != '${sneakersId}'","+created",150)
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
                sneakers[0][4],
                        sneakers[0][2]

            )

            var MaxLines = remember { mutableStateOf(3) }
            Column(modifier = Modifier.horizontalScroll(rememberScrollState())) {

                Text(
                    sneakers[0][5],
                    style = TextInfo,
                    modifier = Modifier.padding(top = 33.dp),
                    maxLines = MaxLines.value,
                    color = colorResource(R.color.hint),
                    overflow = TextOverflow.Ellipsis
                )

                Row(modifier = Modifier.padding(top = 10.dp).fillMaxWidth()) {
                    Text(
                        "Подробнее",
                        modifier = Modifier.fillMaxWidth().clickable(onClick = {
                            if (MaxLines.value < 10) MaxLines.value = 15
                            else MaxLines.value = 3
                        }),
                        style = TextFieldPlace,
                        color = colorResource(R.color.accent),
                        textAlign = TextAlign.End
                    )

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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(viewModel.getImage(collectionId, idSneak, listImage[selectedIndex]))
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)).background(Color.White),
                contentScale = ContentScale.FillWidth,
            )
        }
        // Миниатюры
        if (listImage.isNotEmpty()) {
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

                for (i in 0..listImage.size-1) {

                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {selectedIndex = i}.clip(shape = RoundedCornerShape(12.dp))
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(viewModel.getImage(collectionId, idSneak, listImage[i]))
                                .size(Size.ORIGINAL)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp).clip(RoundedCornerShape(4.dp)).background(Color.White),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
        }
    }
}