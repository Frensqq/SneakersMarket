package com.example.createinlager.presentation.screen.markets

import android.icu.text.StringSearch
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.createinlager.R
import com.example.createinlager.data.ConverCartToArrayArray
import com.example.createinlager.data.ConverToArrayArray
import com.example.createinlager.data.ConverToFavoriteArrayArray
import com.example.createinlager.data.model.FavoriteList
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.domain.state.ResultState
import com.example.createinlager.presentation.screen.viewModels.MarketViewModel
import com.example.createinlager.presentation.screen.viewModels.viewMarketCart
import com.example.createinlager.presentation.theme.ui.TextFieldPlace
import com.example.createinlager.presentation.theme.ui.bottomText
import com.example.createinlager.presentation.theme.ui.miniTextButton
import com.example.createinlager.presentation.theme.ui.textCategory
import com.example.createinlager.presentation.theme.ui.textInFiedMarket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.get


@Composable
fun MarketTextField(text: String, fraction: Float, state: Boolean, viewModel: MarketViewModel = viewModel()): Array<Array<String>> {

    val SneakersList = viewModel.sneakers.collectAsState()
    var sneakers: Array<Array<String>> = emptyArray()
    if (SneakersList.value.isNotEmpty()) {
        sneakers = ConverToArrayArray(SneakersList)
    }

    val string = remember { mutableStateOf("") }

    OutlinedTextField(
        value = string.value,
        enabled = state,
        onValueChange = {string.value = it},
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
            focusedTextColor = colorResource(R.color.text),
            disabledContainerColor = colorResource(R.color.white)

        ),
       trailingIcon = if (state) {{Row{
           Box(modifier = Modifier.height(24.dp).width(2.dp).background(colorResource(R.color.subtextdark)))

           Icon(
           bitmap = ImageBitmap.imageResource(R.drawable.micro),
           tint = colorResource(R.color.hint),
           contentDescription = null,
           modifier = Modifier.padding(horizontal = 12.dp).size(24.dp)
       )}
                      }} else {{}},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            viewModel.SneakersImport("name ~'${string.value}'", "+created", 150)
        }
        ),
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        )
    return sneakers

}

@Composable
fun CategoryRow(token:String, id:String, typeCross:String, navController: NavController){

    val category = listOf("Все", "Outdoor", "Tennis", "Running")

    LazyRow() {

        items(category.size) {index ->

            Button(
                onClick = {
                    navController.navigate("CatalogSneakers/${category[index]}/${id}/${token}")
                },
                modifier = Modifier.padding(start = 20.dp ,end = if(index == category.size-1){20.dp} else 0.dp).height(40.dp).width(108.dp).shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (typeCross == category[index]) colorResource(R.color.accent) else colorResource(R.color.white),
                ),
            ) {
                Text(
                    category[index],
                    style = textCategory,
                    color = if (typeCross == category[index]) colorResource(R.color.white) else colorResource(R.color.hint),
                    textAlign = TextAlign.Center
                )
            }

        }

    }

}

//favorite1: Int, Cart1: Int, listFavorite: Array<Array<String>>,listCart: Array<Array<String>>,

@Composable
fun productСard(favorite: Int, listFavorite: Array<Array<String>>,Cart: Int, listCart: Array<Array<String>>, iduser:String,token: String, sneakers: Array<String>, navController: NavController, viewModel: MarketViewModel = viewModel (), viewModelCart: viewMarketCart = viewModel ()){


    var loved by remember { mutableStateOf(false) }
    var inCart by remember { mutableStateOf(false) }
    var CurretidFavorite by remember { mutableStateOf("") }
    var CurretidInCarts by remember { mutableStateOf("") }

    val idFavorite =remember { mutableStateOf("") }

    if (favorite != (-1)) {
        loved = true
        CurretidFavorite = listFavorite[favorite][1]
    }

    if (Cart != (-1)) {
       inCart = true
        CurretidInCarts = listCart[Cart][0]
    }

    if (sneakers.isNotEmpty()) {

        Box(
            modifier = Modifier.width(160.dp).height(182.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.white))
                .clickable(onClick = {navController.navigate("Details/${sneakers[2]}/${iduser}/${token}/${CurretidFavorite}/${CurretidInCarts}")})
        ) {

            Column(modifier = Modifier.padding(top = 9.dp, start = 9.dp, end = 9.dp).fillMaxSize()) {


                Box() {

                    IconButton(
                        onClick = {
                            if (loved){
                                viewModel.DeleteFavorite(CurretidFavorite)

                                viewModel.ViewFavorite(
                                    "(iduser = '$iduser')&&(idsneakers = '${sneakers[2]}')",
                                )


                                loved = false
                            }
                            else{
                                viewModel.CreateFavorite(iduser,sneakers[2])
                                viewModel.ViewFavorite(
                                    "(iduser = '$iduser')&&(idsneakers = '${sneakers[2]}')",
                                )

                                loved = true
                            }


                        },
                        modifier = Modifier.size(28.dp)
                            .clip(CircleShape)
                    ) {

                        Box(modifier = Modifier.size(28.dp)
                            .clip(CircleShape)
                            .background(colorResource(R.color.background)), contentAlignment = Alignment.Center) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = if (loved) R.drawable.heart else R.drawable.empty_heart),
                                modifier = Modifier.size(16.dp),
                                contentDescription = null,
                                tint = if (loved) colorResource(R.color.red) else colorResource(R.color.hint)
                            )
                        }

                    }



                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(top = 9.dp)
                    ) {
                        if (sneakers[8].isNotEmpty()) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(viewModel.getImage(sneakers[0],sneakers[2],sneakers[8]))
                                    .size(Size.ORIGINAL)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier.heightIn(max = 70.dp).fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.FillHeight,
                            )
                        } else {
                            Image(
                                bitmap = ImageBitmap.imageResource(R.drawable.stockimage),
                                modifier = Modifier.heightIn(max = 70.dp).fillMaxWidth(),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight
                            )
                        }
                    }
                }

                Column(modifier = Modifier.padding(top = 12.dp)) {


                    Text(
                        sneakers[6], style = miniTextButton, color = colorResource(R.color.accent)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(sneakers[3], style = bottomText, color = colorResource(R.color.hint))

                    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {

                        Text(
                            "₽" + sneakers[4],
                            style = TextFieldPlace,
                            color = colorResource(R.color.text),
                        )
                    }

                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(modifier = Modifier
                    .size(34.dp).clip(RoundedCornerShape(topStart = 16.dp)).background(colorResource(R.color.accent))) {

                    IconButton(
                        onClick = {
                            if (!inCart) {
                                viewModelCart.addtocart(iduser,sneakers[2])

                                inCart = !inCart
                            } else {
                                viewModelCart.delCart(CurretidInCarts)
                                inCart = !inCart

                            }
                        },
                        modifier = Modifier.size(34.dp)
                    ) {
                        if (!inCart) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(R.drawable.plus),
                                modifier = Modifier.size(18.dp),
                                contentDescription = null,
                                tint = colorResource(R.color.white)
                            )
                        } else {

                            Icon(
                                bitmap = ImageBitmap.imageResource(R.drawable.cart),
                                contentDescription = null,
                                modifier = Modifier.size(12.dp),
                                tint = colorResource(R.color.white)

                            )
                        }
                    }
                }
            }
        }
    }
}


fun isFaforite(idsneakers:String, favoriteList: Array<Array<String>>): Int{

    for(i in favoriteList.indices){
        if(idsneakers == favoriteList[i][2]){
            return i
        }
    }
    return -1
}
fun isInCart(idsneakers:String, cartList: Array<Array<String>>): Int{

    for(i in cartList.indices){
        if(idsneakers == cartList[i][1]){
            return i
        }
    }
    return -1
}

@Composable
fun listFavorite(filter:String, viewModel: MarketViewModel = viewModel()): Array<Array<String>>{

    viewModel.ViewFavorite(filter)

    val favorite = viewModel.favorites.collectAsState()

    return ConverToFavoriteArrayArray(favorite)
}

@Composable
fun listCart(filter:String, viewModel: viewMarketCart = viewModel()): Array<Array<String>>{

    viewModel.viewCart(filter, "+created",150)

    val Cart = viewModel.Carts.collectAsState()

    return ConverCartToArrayArray(Cart)
}

@Composable
fun columnProducts(sneakers: Array<Array<String>>, iduser: String,token: String,  navController: NavController){


   val listFavorite = listFavorite("iduser = '$iduser'")
    val listCart = listCart("iduser = '$iduser'")

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 15.dp,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth()
    ) {
        items(sneakers.size) { index ->
            productСard(
                isFaforite(
                    sneakers[index][2],
                    listFavorite
                ),
                listFavorite,
                isInCart(
                    sneakers[index][2],
                    listCart
                ),
                listCart,
                iduser,
                token,
                sneakers[index],
                navController

            )
        }
    }
}


@Composable
fun navigationBar(userId:String, token: String,navController: NavController){
    Box(modifier = Modifier.fillMaxSize().height(116.dp),  contentAlignment = Alignment.BottomCenter,){

        IconButton(
            onClick = {navController.navigate("Cart/${userId}/${token}")},
            modifier =  Modifier.padding(bottom = 60.dp).height(60.dp).width(60.dp)
                .fillMaxSize()
                .clip(CircleShape)
                .background(colorResource(R.color.accent)) // Размер кнопки
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.bag),
                modifier = Modifier.fillMaxSize(0.5f),
                contentDescription = null,
                tint = colorResource(R.color.block)
            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){

            Image(bitmap = ImageBitmap.imageResource(R.drawable.homenav), contentDescription =null, modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.FillWidth )
            RowInBox(userId, token, navController)

        }

    }
}

@Composable
fun RowInBox(userId:String, token: String,navController: NavController){


    Row(modifier = Modifier.fillMaxWidth().padding(30.dp).height(35.dp), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {

        IconButton(
            onClick = {navController.navigate("Home/${userId}/${token}")},
            modifier =  Modifier
                .fillMaxHeight() // Размер кнопки
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.home),
                modifier = Modifier.fillMaxSize(0.7f),
                contentDescription = null,
                tint = colorResource(R.color.accent)
            )
        }

        IconButton(
            onClick = {navController.navigate("Favorite/${userId}/${token}")},
            modifier =  Modifier
                .fillMaxHeight() // Размер кнопки
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.empty_heart),
                modifier = Modifier.fillMaxSize(0.7f),
                contentDescription = null,
                tint = colorResource(R.color.hint)
            )
        }

        Spacer(modifier = Modifier.width(30.dp))

        IconButton(
            onClick = {},
            modifier =  Modifier
                .fillMaxHeight() // Размер кнопки
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.notification),
                modifier = Modifier.fillMaxSize(0.7f),
                contentDescription = null,
                tint = colorResource(R.color.hint)
            )
        }

        IconButton(
            onClick = {  },
            modifier =  Modifier
                .fillMaxHeight() // Размер кнопки
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.people),
                modifier = Modifier.fillMaxSize(0.7f),
                contentDescription = null,
                tint = colorResource(R.color.hint)
            )
        }



    }


}





@Preview
@Composable
fun testComponents() {

    //MarketTextField("Поиск",1f)

    //CategoryRow()


}