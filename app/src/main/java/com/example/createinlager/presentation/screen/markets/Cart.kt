package com.example.createinlager.presentation.screen.markets

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.data.ConverCartToArrayArray
import com.example.createinlager.data.ConverToArrayArray
import com.example.createinlager.data.ConvertUserToArrayArray
import com.example.createinlager.domain.state.ResultState
import com.example.createinlager.presentation.screen.AccentLongButton
import com.example.createinlager.presentation.screen.ErrorAunth
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.viewModels.MarketViewModel
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.screen.viewModels.ViewOrders
import com.example.createinlager.presentation.screen.viewModels.viewMarketCart
import com.example.createinlager.presentation.theme.ui.TextFieldPlace
import com.example.createinlager.presentation.theme.ui.TitleCategoryType
import com.example.createinlager.presentation.theme.ui.bottomText
import com.example.createinlager.presentation.theme.ui.checkouttype
import kotlin.Array

@Composable
fun Cart(userId: String, token: String, navController: NavController, viewModel: MarketViewModel = viewModel(), viewMarketCart: viewMarketCart = viewModel(),userViewModel: UserViewModel = viewModel(),viewModerOrders: ViewOrders = viewModel() ){

    var isInitialized by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.SneakersImport("id != 'null'","+created",150)
            viewMarketCart.viewCart("iduser = '$userId'",  "+created", 150)
            userViewModel.ViewUser(userId, token)
            isInitialized = true
        }
    }

    var StateScreen = remember { mutableStateOf(true) }
    val userData by userViewModel.userData.collectAsState()

    var userList = ConvertUserToArrayArray(userData)

    val SneakersInCartClass = viewMarketCart.Carts.collectAsState()
    var sneakersInCart = ConverCartToArrayArray(SneakersInCartClass)

    val SneakersClass = viewModel.sneakers.collectAsState()
    val sneakers = ConverToArrayArray(SneakersClass)

    val ListInfoToOrder = mutableListOf<Array<String>>()

    var totalCount = remember { mutableStateOf(0) }
    var count = 0

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

        if (StateScreen.value){
        Spacer(modifier = Modifier.height(16.dp))

        for (i in 0..sneakersInCart.size-1){
            count += sneakersInCart[i][2].toInt()
        }

            if ((count % 10 == 1) && (count % 100 != 11) && (count % 1000 != 111)) {
                Text("$count товар", style = bottomText, color = colorResource(R.color.text))
            } else if ((count % 10 in 2..4) && (count % 100 !in 12..14)) {
                Text("$count товара", style = bottomText, color = colorResource(R.color.text))
            } else {
                Text("$count товаров", style = bottomText, color = colorResource(R.color.text))
            }

        Spacer(modifier = Modifier.height(13.dp))

        if (sneakersInCart.isNotEmpty()) {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1),
                verticalItemSpacing = 14.dp,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(sneakersInCart.size) { index ->

                    var CurrentSneakers: Array<String> = emptyArray()

                        for (i in 0..sneakers.size-1) {
                            if (sneakersInCart[index][1] == sneakers[i][2]) {
                                CurrentSneakers = sneakers[i]
                            }
                        }

                    if (CurrentSneakers.isNotEmpty()){

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        viewMarketCart.viewCart("iduser = '$userId'",  "+created", 150)

                        BlueBox(sneakersInCart[index][0], sneakersInCart[index][1], sneakersInCart[index][2], userId)

                        SneakersRow(CurrentSneakers[3], CurrentSneakers[4])

                        Box(modifier = Modifier.height(104.dp).width(58.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(colorResource(R.color.red)), contentAlignment = Alignment.Center){

                            Icon(bitmap = ImageBitmap.imageResource(R.drawable.trash),
                                contentDescription = null,
                                tint = colorResource(R.color.block),
                                modifier = Modifier.size(20.dp).clickable(onClick = {
                                    viewMarketCart.delCart(sneakersInCart[index][0])
                                    viewMarketCart.viewCart("iduser = '$userId'",  "+created", 150)
                                }))
                        }
                    }
                    }
                }
            }
        } }
        else{
            if (userList.isNotEmpty()){

                Spacer(modifier = Modifier.height(46.dp))

                CheckoutEmpty(userList, userId, token )
            }
        }
    }

    var costSneakers =0
    var costOrder=0

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        Box(modifier = Modifier.fillMaxWidth().height(258.dp).background(colorResource(R.color.block))){
            Column {

                StateScreen.value =
                    ButtonMenu(sneakersInCart, sneakers, StateScreen.value, "Оформить заказ", onCostSChange = { cost->
                        costSneakers = cost},  onCostOrChange = { cost->
                        costOrder = cost}
                    )
                Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                    AccentLongButton(
                        {
                            if (StateScreen.value) {
                                StateScreen.value = !StateScreen.value
                            } else {
                                userViewModel.ViewUser(userId, token)
                                viewModerOrders.CreateOrder(userId,sneakers[0][2],userList,costSneakers,costOrder)


                            }
                        },
                        if (StateScreen.value) {
                            "Оформить заказ"
                        } else {
                            "Подтвердить"
                        },
                        true
                    )


                    val result = viewModerOrders.resultState.collectAsState()
                    val resultPoz = viewModerOrders.resultStatePoz.collectAsState()
                    val id = viewModerOrders.id.value


                    when (result.value) {
                        is ResultState.Error -> {
                            ErrorAunth((result.value as ResultState.Error).message, "Ошибка добавления заказа")
                        }
                        ResultState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .clip(RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center

                            ) {
                                CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(top= 400.dp), strokeWidth = 10.dp, color = colorResource(R.color.accent))
                            }
                        }
                        ResultState.Initialized -> {
                        }
                        is ResultState.Success -> {
                            for (i in 0..sneakersInCart.size-1) {
                                for (j in 0..sneakers.size - 1) {
                                    if (sneakersInCart[i][1] == sneakers[j][2]) {
                                         viewModerOrders.CreatePozition(id,sneakersInCart[i][1],sneakersInCart[i][2],sneakers[j][4])
                                    }
                                }
                            }

                        }
                    }
                    when (resultPoz.value) {
                        is ResultState.Error -> {
                        }
                        ResultState.Loading -> {
                        }
                        ResultState.Initialized -> {
                        }
                        is ResultState.Success -> {

                            Checkout(navController,"Home/${userId}/${token}" )
                        }
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

@Composable
fun ButtonMenu(  ArrayInCart:Array<Array<String>>, twoDArray:Array<Array<String>>, toChecking:Boolean, textButton: String, onCostSChange: (Int) -> Unit, onCostOrChange: (Int) -> Unit): Boolean {

    var globalCost =0
    var count =0

    var StateScreen = toChecking

    for (i in 0..ArrayInCart.size-1){

        for (j in 0 ..twoDArray.size-1){
            if (twoDArray[j][2]  ==ArrayInCart[i][1]){
                globalCost += (ArrayInCart[i][2].toInt()*twoDArray[j][4].toInt())
                count+=ArrayInCart[i][2].toInt()
            }
        }
    }

    count*=100
Column(modifier = Modifier.padding(horizontal = 20.dp)) {


    Row(modifier = Modifier.fillMaxWidth().padding(top= 34.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Сумма", style = bottomText, color = colorResource(R.color.subtextdark))
        Text("₽" + globalCost.toString(), style = bottomText, color = colorResource(R.color.text))
        onCostSChange(globalCost)

    }
    Row(modifier = Modifier.fillMaxWidth().padding(top= 11.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Доставка", style = bottomText, color = colorResource(R.color.subtextdark))
        Text("₽" + count.toString(), style = bottomText, color = colorResource(R.color.text))

    }

    Row(modifier = Modifier.fillMaxWidth().padding(top = 19.dp), horizontalArrangement = Arrangement.SpaceBetween) {

        for(i in 0..56){
            Box(modifier = Modifier.width(6.dp).height(2.dp).background(color = if(i%2!=0){colorResource(R.color.block)} else {colorResource(R.color.subtextdark)}))
        }
    }

    Row(modifier = Modifier.fillMaxWidth().padding(top= 34.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Итого", style = bottomText, color = colorResource(R.color.text))
        Text(
            "₽" + (globalCost + count).toString(),
            style = bottomText,
            color = colorResource(R.color.accent)
        )
        onCostOrChange(globalCost + count)
    }

    Spacer(modifier = Modifier.height(34.dp))


}
    return StateScreen
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

@Composable
fun Checkout(navController: NavController, Road: String){

    AlertDialog(
        onDismissRequest = { },
        containerColor = Color.White,
        modifier = Modifier.fillMaxWidth().height(375.dp),
        icon = {
            Box(modifier = Modifier.padding(top=20.dp).size(134.dp).clip(RoundedCornerShape(100))
                .background(color = Color(0xffDFEFFF)),
                contentAlignment = Alignment.Center) {
                Image(
                    ImageBitmap.imageResource(R.drawable.happy),
                    contentDescription = null,
                    modifier = Modifier.size(86.dp)
                )
            }
        },
        title = {
            Text(
                text = "Вы успешно оформили заказ",
                style = checkouttype,
                color = colorResource(R.color.text),
                modifier = Modifier.fillMaxWidth(0.75f),
                textAlign = TextAlign.Center,
            )
        },
        confirmButton = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Button(
                    onClick = {navController.navigate(Road)},
                    modifier = Modifier.height(51.dp).width(234.dp).clip(RoundedCornerShape(16.dp))
                        .background(colorResource(R.color.accent)),
                    colors = ButtonColors(
                        containerColor = colorResource(R.color.accent),
                        contentColor = colorResource(R.color.block),
                        disabledContainerColor = colorResource(R.color.accent),
                        disabledContentColor = colorResource(R.color.block),
                    )
                ) {

                    Text("Вернуться к покупкам", style = bottomText)

                }
            }
        },

        )

}