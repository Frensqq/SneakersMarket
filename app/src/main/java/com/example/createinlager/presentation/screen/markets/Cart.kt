package com.example.createinlager.presentation.screen.markets

import android.inputmethodservice.Keyboard
import android.text.Layout
import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.data.ConverCartToArrayArray
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.viewModels.MarketViewModel
import com.example.createinlager.presentation.screen.viewModels.viewMarketCart
import com.example.createinlager.presentation.theme.ui.TextFieldPlace
import com.example.createinlager.presentation.theme.ui.TitleCategoryType
import com.example.createinlager.presentation.theme.ui.bottomText

@Composable
fun Cart(userId: String, token: String, navController: NavController, viewModel: MarketViewModel = viewModel(), viewMarketCart: viewMarketCart = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewMarketCart.viewCart("iduser = '$userId'",  "+created", 150)
            viewModel.SneakersImport("id != 'null'","+created",150)

            isInitialized = true
        }
    }

    val SneakersInCartClass = viewMarketCart.Carts.collectAsState()
    var sneakersInCart = ConverCartToArrayArray(SneakersInCartClass)

    val SneakersClass = viewModel.sneakers.collectAsState()




    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.background)).padding(horizontal = 20.dp)) {

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            buttonBack(navController, "Home/${userId}/${token}")

            Text(
                text = "Корзина",
                modifier = Modifier.fillMaxWidth(),
                style = TitleCategoryType,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(" товаров", style = bottomText, color = colorResource(R.color.text))

        Spacer(modifier = Modifier.height(13.dp))

        if (sneakersInCart.isNotEmpty()) {


            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1),
                verticalItemSpacing = 14.dp,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(sneakersInCart.size) { index ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        BlueBox(sneakersInCart[index][0], sneakersInCart[index][1], sneakersInCart[index][2], userId)

                        SneakersRow("TEst Sneakers", "7400")

                        RedBox(sneakersInCart[index][0])

                    }

                }
            }
        }
    }


}



@Composable
fun BlueBox(id: String,idSneakers: String,count: String,userId: String,viewMarketCart: viewMarketCart = viewModel() ): Int{

    var countSneakers = remember { mutableStateOf(count.toInt())}

    Box(modifier = Modifier.height(104.dp).width(58.dp).clip(RoundedCornerShape(8.dp)).background(colorResource(R.color.accent))){
        Column(modifier = Modifier.padding(vertical = 14.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {

            Icon(bitmap = ImageBitmap.imageResource(R.drawable.plus),
                contentDescription = null,
                tint = colorResource(R.color.block),
                modifier = Modifier.size(14.dp).clickable(onClick = {

                    if (countSneakers.value<99) {
                        countSneakers.value += 1
                        viewMarketCart.UpdateCart(id,userId,idSneakers, countSneakers.value)

                    }

                }))

            Text(text = countSneakers.value.toString(), style = TextFieldPlace, letterSpacing = 1.sp, color = colorResource(R.color.block))

            Icon(bitmap = ImageBitmap.imageResource(R.drawable.minus),
                contentDescription = null,
                tint = colorResource(R.color.block),
                modifier = Modifier.size(14.dp).clickable(onClick = {

                    if (countSneakers.value>1) {
                        countSneakers.value -= 1
                        viewMarketCart.UpdateCart(id,userId,idSneakers, countSneakers.value)
                    }
                }))

        }
    }

    return countSneakers.value
}

@Composable
fun SneakersRow(NaneSneakers: String, Cost: String){

    Row(modifier = Modifier.padding(horizontal = 10.dp).height(104.dp).fillMaxWidth(0.75f).clip(RoundedCornerShape(8.dp)).background(colorResource(R.color.block)), verticalAlignment = Alignment.CenterVertically){

        Box(modifier = Modifier.padding(horizontal = 10.dp).height(85.dp).width(87.dp).clip(RoundedCornerShape(16.dp)).background(colorResource(R.color.background)), contentAlignment = Alignment.Center) {
            Image(bitmap = ImageBitmap.imageResource(R.drawable.stockimage), contentDescription = null, contentScale = ContentScale.FillWidth)
        }

        Column(modifier = Modifier.padding( end = 16.dp)) {

            Text(NaneSneakers, style = TextFieldPlace,  color = Color(0xff1A2530), maxLines = 2)

            Spacer(modifier = Modifier.height(6.dp))

            Text("₽"+Cost, style = TextFieldPlace, color = Color(0xff1A2530))

        }
    }

}

@Composable
fun RedBox(id: String, viewMarketCart: viewMarketCart = viewModel() ) {



    Box(modifier = Modifier.height(104.dp).width(58.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(colorResource(R.color.red)), contentAlignment = Alignment.Center){

        Icon(bitmap = ImageBitmap.imageResource(R.drawable.trash),
            contentDescription = null,
            tint = colorResource(R.color.block),
            modifier = Modifier.size(20.dp).clickable(onClick = {
                viewMarketCart.delCart(id)
            }))
    }

}



@Preview
@Composable
fun testCart(){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

        //BlueBox("2")

        SneakersRow("TEst Sneakers", "7400")

        RedBox("test")

    }
}