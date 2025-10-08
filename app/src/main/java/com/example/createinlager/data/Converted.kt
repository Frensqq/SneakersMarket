package com.example.createinlager.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import com.example.createinlager.data.model.FavoriteList
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.data.model.Sneakers
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

