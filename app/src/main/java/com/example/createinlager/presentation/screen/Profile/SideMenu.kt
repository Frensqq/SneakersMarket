package com.example.createinlager.presentation.screen.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.createinlager.R
import com.example.createinlager.presentation.screen.viewModels.UserViewModel
import com.example.createinlager.presentation.theme.ui.ProfileName
import com.example.createinlager.presentation.theme.ui.bottomText

@Composable
fun SideMenu(userId: String, token: String, navController: NavController, viewModel: UserViewModel = viewModel()){

    var isInitialized by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isInitialized) {
            viewModel.ViewUser(userId, token )
            isInitialized = true
        }
    }

    val userData by viewModel.userData.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color(0xff48b2e7))) {

        
        Row(modifier = Modifier.fillMaxWidth(1f)) {
            Column(modifier = Modifier.padding(start = 20.dp).fillMaxWidth(0.75f)) {

                Spacer(modifier = Modifier.height(78.dp))

                Box(
                    modifier = Modifier.padding(start = 16.dp).size(96.dp)
                        .clip(RoundedCornerShape(100)).background(colorResource(R.color.background))
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

                Text(
                    (userData?.name ?: ("User")) + " " + (userData?.surname ?: ("")),
                    style = ProfileName,
                    color = colorResource(R.color.block),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(57.dp))

                ProfileRow(R.drawable.people, "Профиль", "ProfileScreen/${userId}/${token}",
                    navController)
                Spacer(modifier = Modifier.height(30.dp))

                ProfileRow(R.drawable.bag, "Корзина", "Cart/${userId}/${token}", navController)
                Spacer(modifier = Modifier.height(30.dp))

                ProfileRow(R.drawable.empty_heart, "Избранное", "Favorite/${userId}/${token}",
                    navController)
                Spacer(modifier = Modifier.height(31.dp))

                ProfileRow(R.drawable.delivery, "Заказы", "", navController)
                Spacer(modifier = Modifier.height(28.dp))

                ProfileRow(R.drawable.notification, "Уведомления", "", navController)
                Spacer(modifier = Modifier.height(30.dp))

                ProfileRow(R.drawable.settings, "Настройки", "", navController)
                Spacer(modifier = Modifier.height(38.dp))

                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x3bf7f7f9)))
                Spacer(modifier = Modifier.height(30.dp))

                 ProfileRow(R.drawable.exit, "Выйти", "SingIn", navController)

            }


            Box(modifier = Modifier.padding( start = 241.dp, top = 97.dp, bottom = 96.86.dp)
                .wrapContentWidth(unbounded = true, align = Alignment.End).rotate(-3.43f).clip(RoundedCornerShape(25.dp)),
                contentAlignment = Alignment.CenterEnd) {

                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.homepage),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
            }
        }
    }
}

@Composable
fun ProfileRow(icon: Int, text: String, Road: String, navController: NavController){
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = {navController.navigate(Road)})) {

        Icon(bitmap = ImageBitmap.imageResource(icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colorResource(R.color.block))

        Spacer(modifier = Modifier.width(27.dp))

        Text(text, style = bottomText, color = colorResource(R.color.block))

    }
}

@Preview
@Composable
fun testSideMenu(){





}

