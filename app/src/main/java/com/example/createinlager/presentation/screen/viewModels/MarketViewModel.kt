package com.example.createinlager.presentation.screen.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createinlager.data.model.ErrorResponce
import com.example.createinlager.data.model.FavoriteList
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.data.model.SneakersView
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.data.remote.PocketBaseApiService
import com.example.createinlager.domain.model.UserRequest
import com.example.createinlager.domain.model.favoriteRequest
import com.example.createinlager.domain.state.ResultState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketViewModel: ViewModel() {

    private val apiService = PocketBaseApiService.instance
    private val _resultState = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _SneakersList = mutableStateOf<List<Sneakers>>(emptyList())
    val sneakersList: State<List<Sneakers>>  = _SneakersList

    private val _sneakersImage = MutableStateFlow<List<String>>(emptyList())
    val sneakersImage: StateFlow<List<String>> get() = _sneakersImage.asStateFlow()
    private val _id = mutableStateOf("")
    val id: State<String> = _id
    private val _sneakers = MutableStateFlow<List<Sneakers>>(emptyList())
    val sneakers: StateFlow<List<Sneakers>> get() = _sneakers.asStateFlow()

    private val _favorites = MutableStateFlow<List<FavoriteResponse>>(emptyList())
    val favorites: StateFlow<List<FavoriteResponse>> get() = _favorites.asStateFlow()


    fun getImage(collectionId:String, idsneakers: String, image:String): String {
        val imageUrl =
            "http://10.0.2.2:8090/api/files/${collectionId}/${idsneakers}/${image}"
        Log.i("Image", imageUrl)
        return imageUrl
    }


    fun SneakersImport(filter: String,sort: String,perPage: Int){
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            apiService.ViewSneakers(
                filter,
                sort,
                perPage
            ).enqueue(object : Callback<SneakersView> {
                override fun onResponse(
                    call: Call<SneakersView>,
                    response: Response<SneakersView>
                ) {
                    try {
                        response.body()?.let{
                            _SneakersList.value = it.items
                            _sneakers.value = it.items
                            _resultState.value = ResultState.Success("Success")
                            Log.d("SneakersView", "Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java).message
                                _resultState.value = ResultState.Error(message)

                            } catch (ex: Exception) {
                                Log.e(
                                    "SneakersView",
                                    "Failed to parse error response: ${ex.message}"
                                )
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("SneakersView", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }
                override fun onFailure(call: Call<SneakersView>, t: Throwable) {
                    _resultState.value = ResultState.Error(t.message.toString())
                    Log.e("SneakersView", t.message.toString())
                }
            })
        }
    }

    fun ViewFavorite(filter: String){
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            apiService.checkFavorite(
                filter,
            ).enqueue(object : Callback<FavoriteList> {
                override fun onResponse(
                    call: Call<FavoriteList>,
                    response: Response<FavoriteList>
                ) {
                    try {
                        response.body()?.let{
                            _favorites.value = it.items
                            _resultState.value = ResultState.Success("Success")
                            Log.d("SneakersView", "Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java).message
                                _resultState.value = ResultState.Error(message)

                            } catch (ex: Exception) {
                                Log.e(
                                    "SneakersView",
                                    "Failed to parse error response: ${ex.message}"
                                )
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("SneakersView", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }
                override fun onFailure(call: Call<FavoriteList>, t: Throwable) {
                    _resultState.value = ResultState.Error(t.message.toString())
                    Log.e("SneakersView", t.message.toString())
                }
            })
        }
    }

    fun CreateFavorite(iduser:String, idsneakers:String){
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            apiService.addFavorite(
                favoriteRequest(
                    iduser ,
                    idsneakers
                )
            ).enqueue(object : Callback<FavoriteList> {
                override fun onResponse(
                    call: Call<FavoriteList>,
                    response: Response<FavoriteList>
                ) {
                    try {
                        response.body()?.let{
                            _favorites.value = it.items
                            _id.value=it.items[0].id?:"not"
                            _resultState.value = ResultState.Success("Success")
                            Log.d("AddFavorite", "Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java).message
                                _resultState.value = ResultState.Error(message)

                            } catch (ex: Exception) {
                                Log.e(
                                    "AddFavorite",
                                    "Failed to parse error response: ${ex.message}"
                                )
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("AddFavorite", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }
                override fun onFailure(call: Call<FavoriteList>, t: Throwable) {
                    _resultState.value = ResultState.Error(t.message.toString())
                    Log.e("AddFavorite", t.message.toString())
                }
            })
        }
    }

    fun DeleteFavorite(id:String){
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            apiService.deleteFavorite(
                id
            ).enqueue(object : Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>
                ) {
                    try {
                        response.body()?.let{
                            _resultState.value = ResultState.Success("Success")
                            Log.d("DelFavorite", "Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java).message
                                _resultState.value = ResultState.Error(message)

                            } catch (ex: Exception) {
                                Log.e(
                                    "DelFavorite",
                                    "Failed to parse error response: ${ex.message}"
                                )
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("DelFavorite", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    _resultState.value = ResultState.Error(t.message.toString())
                    Log.e("DelFavorite", t.message.toString())
                }
            })
        }
    }


    fun getId(): String = _id.value?:"0"
}