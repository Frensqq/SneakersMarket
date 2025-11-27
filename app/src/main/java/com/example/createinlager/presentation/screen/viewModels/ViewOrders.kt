package com.example.createinlager.presentation.screen.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createinlager.data.model.ErrorResponce
import com.example.createinlager.data.model.FavoriteResponse
import com.example.createinlager.data.model.ListOrderResponse
import com.example.createinlager.data.model.OrderResponse
import com.example.createinlager.data.model.PozitionResponse
import com.example.createinlager.data.model.Sneakers
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.data.remote.PocketBaseApiService
import com.example.createinlager.domain.model.OrderRequest
import com.example.createinlager.domain.model.PozitionRequest
import com.example.createinlager.domain.state.ResultState
import com.example.professionals.data.model.market.InCart
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewOrders: ViewModel() {

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _resultStatePoz = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultStatePoz: StateFlow<ResultState> = _resultStatePoz.asStateFlow()

    private val apiService = PocketBaseApiService.instance


   private val _orders= MutableStateFlow<List<OrderResponse>>(emptyList())
   val orders: StateFlow<List<OrderResponse>> = _orders.asStateFlow()

    private val _id = mutableStateOf("")
    val id: State<String> = _id

    fun CreateOrder(UserId:String, idsneakers:String, userList:Array<String>,cost: Int,costOrder:Int){
        _resultState.value = ResultState.Loading // Устанавливаем состояние загрузки
        viewModelScope.launch {
            apiService.createOrder(
                OrderRequest(
                    idusers = UserId,
                    idsneakers,
                    userList[3],
                    userList[4],
                    userList[5],
                    userList[6],
                    cost = cost.toString(),
                    costOrder = costOrder.toString()
                )
            ).enqueue(
                object : Callback<OrderResponse> {

                    override fun onResponse(
                        call: Call<OrderResponse>,
                        response: Response<OrderResponse>,
                    ) {
                        try {
                            // Если запрос успешен, сохраняем список книг и обновляем состояние
                            response.body()?.let {
                                val id = it.id
                                _id.value = it.id
                                _resultState.value = ResultState.Success("Success")
                                //_Carts.value = it
                                Log.d("createOrder", id)

                            }
                            response.errorBody()?.let {
                                try {
                                    val message =
                                        Gson().fromJson(it.string(), ErrorResponce::class.java)
                                    _resultState.value = ResultState.Error(message.toString())
                                } catch (ex: Exception) {
                                    Log.e("createOrder", "Failed to parse error response: ${ex.message}")
                                    _resultState.value = ResultState.Error(ex.message.toString())
                                }

                            }
                        } catch (exception: Exception) {
                            _resultState.value = ResultState.Error(exception.message.toString())
                            Log.e("createOrder", exception.message.toString())
                        }
                    }

                    override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                        // Обработка ошибки при выполнении запроса
                        _resultState.value = ResultState.Error(t.message.toString())
                        Log.e("createOrder", t.message.toString())
                    }
                })
        }
    }


    fun ViewOrder(filter:String){
        _resultState.value = ResultState.Loading // Устанавливаем состояние загрузки
        viewModelScope.launch {
            apiService.viewOrder(
                filter

            ).enqueue(
                object : Callback<ListOrderResponse> {

                    override fun onResponse(
                        call: Call<ListOrderResponse>,
                        response: Response<ListOrderResponse>,
                    ) {
                        try {
                            // Если запрос успешен, сохраняем список книг и обновляем состояние
                            response.body()?.let {
                                _orders.value = it.item
                                val id = "Success"
                                _resultState.value = ResultState.Success("Success")
                                //_Carts.value = it
                                Log.d("ViewOrders", id)

                            }
                            response.errorBody()?.let {
                                try {
                                    val message =
                                        Gson().fromJson(it.string(), ErrorResponce::class.java)
                                    _resultState.value = ResultState.Error(message.toString())
                                } catch (ex: Exception) {
                                    Log.e("ViewOrders", "Failed to parse error response: ${ex.message}")
                                    _resultState.value = ResultState.Error(ex.message.toString())
                                }

                            }
                        } catch (exception: Exception) {
                            _resultState.value = ResultState.Error(exception.message.toString())
                            Log.e("ViewOrders", exception.message.toString())
                        }
                    }

                    override fun onFailure(call: Call<ListOrderResponse>, t: Throwable) {
                        // Обработка ошибки при выполнении запроса
                        _resultState.value = ResultState.Error(t.message.toString())
                        Log.e("ViewOrders", t.message.toString())
                    }
                })
        }
    }

    fun CreatePozition(orderId: String, sneakersId: String, count: String, cost: String,){
        _resultState.value = ResultState.Loading // Устанавливаем состояние загрузки
        viewModelScope.launch {
            apiService.createPozition(
                PozitionRequest(
                    order_id = orderId,
                    sneakers_id = sneakersId,
                    count = count,
                    cost = cost
                )
            ).enqueue(
                object : Callback<PozitionResponse> {

                    override fun onResponse(
                        call: Call<PozitionResponse>,
                        response: Response<PozitionResponse>,
                    ) {
                        try {
                            // Если запрос успешен, сохраняем список книг и обновляем состояние
                            response.body()?.let {
                                val id = it.id
                                _resultStatePoz.value = ResultState.Success("Success")
                                //_Carts.value = it
                                Log.d("createOrderPozition", id)

                            }
                            response.errorBody()?.let {
                                try {
                                    val message =
                                        Gson().fromJson(it.string(), ErrorResponce::class.java)
                                    _resultStatePoz.value = ResultState.Error(message.toString())
                                } catch (ex: Exception) {
                                    Log.e("createOrderPozition", "Failed to parse error response: ${ex.message}")
                                    _resultStatePoz.value = ResultState.Error(ex.message.toString())
                                }

                            }
                        } catch (exception: Exception) {
                            _resultStatePoz.value = ResultState.Error(exception.message.toString())
                            Log.e("createOrderPozition", exception.message.toString())
                        }
                    }

                    override fun onFailure(call: Call<PozitionResponse>, t: Throwable) {
                        // Обработка ошибки при выполнении запроса
                        _resultStatePoz.value = ResultState.Error(t.message.toString())
                        Log.e("createOrderPozition", t.message.toString())
                    }
                })
        }
    }



}