package com.example.createinlager.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import com.example.createinlager.data.model.FavoriteList
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.data.model.UserResponse
import com.example.professionals.data.model.market.InCart
import com.example.professionals.data.model.market.ListInCart
import kotlin.collections.map

@Composable
fun ConverToArrayArray(sneakers:  State<List<Sneakers>>): Array<Array<String>>{

    val twoDArray = remember(sneakers.value) {
        sneakers.value.map { sneaker ->
            arrayOf(
                sneaker.collectionId, // [0]
                sneaker.collectionName,
                sneaker.id,
                //sneaker.image,
                sneaker.name,
                sneaker.cost,
                sneaker.info,
                sneaker.category,
                sneaker.gender,
                sneaker.Titleimg,
                sneaker.watch,
                sneaker.created,
                sneaker.updated
            )
        }.toTypedArray()
    }
    return twoDArray
}

@Composable
fun ConvertUserToArrayArray(userData:  UserResponse?): Array<String> {
    val userList = remember(userData) {
        if (userData != null) {
            arrayOf(
                userData!!.id,
                userData!!.name,
                userData!!.surname,
                userData!!.email,
                userData!!.phoneNumber,
                userData!!.address,
                userData!!.card,
                userData!!.avatar,
                userData!!.collectionId,
                userData!!.collectionName
            )
        } else {
            emptyArray()
        }
    }
    return userList
}


//@Composable
//fun ConvertUserToArrayArray(users:  UserResponse): Array<Array<String>>{
//
//    val twoDArray = remember(users) {
//        users { user ->
//            arrayOf(
//                user.collectionId, // [0]
//                user.collectionName,
//                user.id,
//                user.name,
//                user.email,
//                user.address,
//                user.avatar,
//                user.card,
//                user.phoneNumber,
//                user.surname,
//                user.created,
//                user.updated
//            )
//        }.toTypedArray()
//    }
//    return twoDArray
//}

@Composable
fun ConverToFavoriteArrayArray(favorits:  State<List<FavoriteResponse>>): Array<Array<String>>{


    val twoDArray = remember(favorits.value) {
        favorits.value.map { favorit ->
            arrayOf(
                favorit.iduser,
                favorit.id,
                favorit.idsneakers
            )
        }.toTypedArray()
    }
    return twoDArray
}

@Composable
fun ConverCartToArrayArray(favorits:  State<List<InCart>>): Array<Array<String>>{


    val twoDArray = remember(favorits.value) {
        favorits.value.map { favorit ->
            arrayOf(
                favorit.id,
                favorit.idsneakers,
                favorit.count.toString()
            )
        }.toTypedArray()
    }
    return twoDArray
}

@Composable
fun ListFavoriteSneakersCreate(ListSneakers: Array<Array<String>>, listFavorite: Array<Array<String>>):Array<Array<String>> {

    val ListFavoriteSneakers = mutableListOf<Array<String>>()

    var k = 0

    for (i in ListSneakers.indices) {
        for (j in listFavorite.indices) {
            if (ListSneakers[i][2] == listFavorite[j][2]) {
                ListFavoriteSneakers.add(ListSneakers[i])
                k +=1
            }
        }
    }
    return ListFavoriteSneakers.toTypedArray()

}

