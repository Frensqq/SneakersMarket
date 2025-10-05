package com.example.createinlager.presentation.screen.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createinlager.data.model.ErrorResponce
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.data.model.SneakersView
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.data.remote.PocketBaseApiService
import com.example.createinlager.domain.model.UserRequest
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

    private val _sneakers = MutableStateFlow<List<Sneakers>>(emptyList())
    val sneakers: StateFlow<List<Sneakers>> get() = _sneakers.asStateFlow()


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

}