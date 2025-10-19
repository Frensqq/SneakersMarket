package com.example.createinlager.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import com.example.createinlager.data.model.FavoriteList
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.data.model.Sneakers
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

