package com.example.createinlager.presentation.screen.Profile

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.createinlager.R
import com.example.createinlager.data.ConvertUserToArrayArray
import com.example.createinlager.presentation.screen.markets.navigationBar
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.theme.ui.ButtonText
import com.example.createinlager.presentation.theme.ui.CategoryName
import com.example.createinlager.presentation.theme.ui.ProfileName
import com.example.createinlager.presentation.theme.ui.TitleCategoryType
import com.google.android.play.integrity.internal.n
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType


@Composable
fun Profile(userId: String, token: String, navController: NavController, viewModel: UserViewModel = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.ViewUser(userId, token )
            isInitialized = true
        }
    }

    val userData by viewModel.userData.collectAsState()
    val ListUser = ConvertUserToArrayArray(userData)

    if (ListUser.isNotEmpty()) {

        var names by remember { mutableStateOf(ListUser[1]) }
        var surnames by remember { mutableStateOf(ListUser[2]) }
        var address by remember { mutableStateOf(ListUser[5]) }
        var phone by remember { mutableStateOf(ListUser[4]) }
        var state by remember { mutableStateOf(true) }


        Column(modifier = Modifier.fillMaxSize().background(colorResource(R.color.block))) {


            Spacer(modifier = Modifier.height(63.dp))

            Row(
                modifier = Modifier.padding(start = 18.dp, end = 22.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                if (state) {
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.burger),
                        contentDescription = null,
                        modifier = Modifier.height(18.dp).clickable(onClick = {navController.navigate("Profile/${userId}/${token}")}),
                        contentScale = ContentScale.FillHeight
                    )

                    Text("Профиль", style = TitleCategoryType, color = colorResource(R.color.text))

                    Box(
                        modifier = Modifier.size(25.dp)
                            .clip(CircleShape)
                            .background(colorResource(R.color.accent))
                            .clickable(onClick = { state = !state }),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            bitmap = ImageBitmap.imageResource(R.drawable.edit),
                            contentDescription = null,
                            tint = colorResource(R.color.block),
                            modifier = Modifier.size(8.5.dp)
                        )

                    }
                } else {

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Button(
                            onClick = {
                                state = !state
                                viewModel.UpdateUser(userId, token, ListUser[3], phone, ListUser[6], address, names, surnames)
                                viewModel.ViewUser(userId, token)
                            },
                            modifier = Modifier
                                .height(32.dp)
                                .padding(start = 86.dp, end = 76.dp).fillMaxSize(),
                            shape = RoundedCornerShape(13.dp),
                            colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = colorResource(R.color.disable),
                                containerColor = colorResource(R.color.accent),
                                contentColor = colorResource(R.color.block)
                            )
                        ) {
                            Text(
                                "Сохранить",
                                style = ButtonText,
                                color = colorResource(R.color.block)
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(if(state) 45.dp else 43.dp))

                Box(
                    modifier = Modifier.size(96.dp)
                        .clip(RoundedCornerShape(100)).background(colorResource(R.color.background)),

                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                viewModel.getImage(
                                    (userData?.collectionId ?: ("")),
                                    userId,
                                    (userData?.avatar ?: (""))
                                )
                            )
                            .size(Size.ORIGINAL)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop


                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "$names $surnames",
                    style = ProfileName,
                    color = colorResource(R.color.text),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(35.dp))

                if (BarcodeType.CODE_128.isValueValid(userId)) {
                    Column(
                        Modifier.height(65.dp).fillMaxWidth()
                            .clickable {
                                navController.navigate("BarcodeFullScreen/${userId}/${token}")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(modifier = Modifier.clip(RoundedCornerShape(16.dp)).background(colorResource(R.color.background)), verticalAlignment = Alignment.Bottom) {

                            Text("открыть", modifier = Modifier.padding(8.dp).height(65.dp).rotate(-90f), textAlign = TextAlign.Center )

                            Barcode(
                                modifier = Modifier.fillMaxSize(),
                                resolutionFactor = 2,
                                width = 330.dp,
                                height = 50.dp,
                                type = BarcodeType.CODE_128,
                                value = userId
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                names = RowInProfile(
                    "Имя",
                    names,
                    state
                )

                surnames = RowInProfile(
                    "Фамилия",
                    surnames,
                    state
                )

                address = RowInProfile(
                    "Адрес",
                    address,
                    state
                )

                phone = RowInProfile(
                    "Телефон",
                    phone,
                    state
                )
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

                navigationBar(userId, token, navController)
            }
        }
    }


}



@Composable
fun RowInProfile(nameCat: String, valueText:String, check: Boolean): String{


    var catText by remember { mutableStateOf(valueText) }

    Column {

        Spacer(modifier = Modifier.height(16.dp))

        Text(nameCat, style = CategoryName, color = colorResource(R.color.text))
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            catText,
            { catText = it },
            textStyle = TextStyle(fontSize = 14.sp),
            enabled = !check,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedContainerColor = Color(0xffF7F7F9),
                unfocusedContainerColor = Color(0xffF7F7F9),
                unfocusedTextColor = colorResource(R.color.text),
                focusedTextColor = colorResource(R.color.text),
                disabledContainerColor = Color(0xffF7F7F9),
                disabledTextColor = colorResource(R.color.text),
                disabledBorderColor = Color.White,
            ),
            trailingIcon =  {Icon(bitmap = ImageBitmap.imageResource(R.drawable.correct),
                contentDescription = null,
                modifier = Modifier.size(10.dp),
                tint = if (check)  Color(0xffeaeaeb) else colorResource(R.color.accent))} ,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
        )
    }

    return catText
}