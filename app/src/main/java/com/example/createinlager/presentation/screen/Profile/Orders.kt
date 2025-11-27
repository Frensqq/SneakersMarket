package com.example.createinlager.presentation.screen.Profile

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.createinlager.R
import com.example.createinlager.data.ConverToArrayArrayOrderList
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.screen.viewModels.ViewOrders
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.TitleCategoryType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun Orders(userId: String, token: String, navController: NavController, viewModel: ViewOrders = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.ViewFavorite("idusers = '$userId'")
            isInitialized = true
        }
    }
    val ListOrders by viewModel.orders.collectAsState()
    val OrdersArray = ConverToArrayArrayOrderList(ListOrders)

    val CurrentDate = CurrentDateTime()

    Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.background))) {

        Spacer(modifier = Modifier.height(48.dp))

        OrdersTitleLine(navController,"Profile/${userId}/${token}")

        Spacer(modifier = Modifier.height(23.dp))

        var LastData = ""

        if (OrdersArray.isNotEmpty()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(1),
                verticalItemSpacing = 14.dp,
                modifier = Modifier.padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {

                items(OrdersArray.size) { index ->

                    Column() {

                        val buyDataTime = parsingByTime(OrdersArray[index][9])

                        var TextData = "${buyDataTime[2].toString()}.${buyDataTime[1].toString()}.${buyDataTime[0].toString()}"

                        if((buyDataTime[0]==CurrentDate[0]) and (buyDataTime[1]==CurrentDate[1])
                                    and (buyDataTime[2]==CurrentDate[2]) and (buyDataTime[3]==CurrentDate[3])){
                            TextData = "Недавний"
                        }
                        else if ((buyDataTime[0]==CurrentDate[0]) and (buyDataTime[1]==CurrentDate[1])
                            and (buyDataTime[2]==CurrentDate[2])){
                            TextData = "Сегодня"
                        }
                        else if ((buyDataTime[0]==CurrentDate[0]) and (buyDataTime[1]==CurrentDate[1])
                            and (buyDataTime[2]==CurrentDate[2]-1)){
                            TextData = "Вчера"
                        }
                        if (TextData != LastData) {
                            Text(TextData)
                        }

                        LastData = TextData

                        var time = ""

                        if(TextData == "Недавний"){
                            time = "${CurrentDate[4] - buyDataTime[4]} минут назад "

                        }
                        else{
                           time=  "${buyDataTime[3].toString()}:${buyDataTime[4].toString()} "
                        }

                        OrdersCard(
                            OrdersArray[index][0],
                            "test",
                            OrdersArray[index][4],
                            OrdersArray[index][3],
                            time
                        )
                    }

                }
            }
        }




    }

}

@Composable
fun OrdersCard(number: String, name:String, cost: String, costOrder: String, time: String) {

    Box(modifier = Modifier.fillMaxWidth()
        .height(105.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(colorResource(R.color.block))){

        Row(modifier = Modifier.padding(10.dp) ){

            Box(modifier = Modifier.fillMaxHeight().width(87.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.background)), contentAlignment = Alignment.Center) {

                Image(bitmap = ImageBitmap.imageResource(R.drawable.stockimage),
                    contentDescription = null, contentScale = ContentScale.FillWidth)

            }

            Spacer(modifier = Modifier.width(15.dp))

            Column(modifier = Modifier.padding(top = 4.dp, bottom = 5.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {

                Text("№ "+number, style = ButtonText, color = colorResource(R.color.accent), maxLines = 1)

                Text(name, style = ButtonText, color = colorResource(R.color.text), maxLines = 2)

                Row() {
                    Text("₽" + costOrder,style = ButtonText, color = colorResource(R.color.text), maxLines = 1)

                    Spacer(modifier = Modifier.width(26.dp))

                    Text("₽" + cost,style = ButtonText, color = colorResource(R.color.hint), maxLines = 1)
                }
            }

            Text(time, style = ButtonText, color = colorResource(R.color.hint), maxLines = 1, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)

        }
    }
}

@Preview
@Composable
fun PreviewOrdersCard() {
    OrdersCard("3463456345","Nike aIR fORCE",  "260","364.96", "10:30")
}


fun CurrentDateTime(): Array<Int>{
    val currentDateTime = LocalDateTime.now()
    val ArrayData: Array<Int> = arrayOf(
        currentDateTime.year,
        currentDateTime.monthValue,
        currentDateTime.dayOfMonth,
        currentDateTime.hour,
        currentDateTime.minute,
        currentDateTime.second
    )
    return ArrayData
}

fun parsingByTime(BuyTime: String): Array<Int>{
    val dateString = BuyTime

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS'Z'")
    val dateTime = LocalDateTime.parse(dateString, formatter)

    val ArrayData: Array<Int> = arrayOf(
        dateTime.year,
        dateTime.monthValue,
        dateTime.dayOfMonth,
        dateTime.hour,
        dateTime.minute,
        dateTime.second
    )

    return ArrayData
}

@Composable
fun OrdersTitleLine(navController: NavController, Road: String){

    Row(
        modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        buttonBack(navController, Road )

        Text("Заказы", style = TitleCategoryType)

        Spacer(modifier = Modifier.size(44.dp))


    }

}