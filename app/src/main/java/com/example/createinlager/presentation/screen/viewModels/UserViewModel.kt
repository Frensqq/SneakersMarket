package com.example.createinlager.presentation.screen.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createinlager.data.model.AuthResponse
import com.example.createinlager.data.model.ErrorResponce
import com.example.createinlager.data.model.OtpResponse
import com.example.createinlager.data.model.UserResponse
import com.example.createinlager.data.remote.PocketBaseApiService
import com.example.createinlager.domain.model.ChangePassRequest
import com.example.createinlager.domain.model.OtpAuth
import com.example.createinlager.domain.model.OtpRequest
import com.example.createinlager.domain.model.UserAuth
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

class UserViewModel(): ViewModel() {

    private val apiService = PocketBaseApiService.instance
    private val _user = mutableStateOf<UserResponse?>(null)
    val user: State<UserResponse?> get() = _user

    private val _id = mutableStateOf("")
    private val _otpCode = mutableStateOf<String>("")
    val otpCode: State<String>  = _otpCode

    private val _token = mutableStateOf<String>("")
    val token: State<String>  = _token

    private val _showSuccessDialog = MutableStateFlow(false)
    val showSuccessDialog: StateFlow<Boolean> = _showSuccessDialog

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _resultEmailState = MutableStateFlow<ResultState>(ResultState.Initialized)
    val resultEmailState: StateFlow<ResultState> = _resultEmailState.asStateFlow()

    fun Registration(name: String, email: String, password: String) {
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            apiService.RegistrationUser(
                UserRequest(
                    name = name,
                    email,
                    password,
                    passwordConfirm = password
                )
            ).enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    try {
                        response.body()?.let {
                            val id = it.id
                            _resultState.value = ResultState.Success("Success")
                            _showSuccessDialog.value = true
                            Log.d("Registration", id)
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java).message
                                _resultState.value = ResultState.Error(message)

                            } catch (ex: Exception) {
                                Log.e(
                                    "Register",
                                    "Failed to parse error response: ${ex.message}"
                                )
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("Register", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _resultState.value = ResultState.Error(t.message.toString())
                    Log.e("Register", t.message.toString())
                }
            })
        }
    }

    fun SingIn(identity: String, password: String) {
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            apiService.AuthPass(
                UserAuth(
                    identity,
                    password
                )
            ).enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    try {
                        response.body()?.let {
                            val token = it.token
                            _token.value = it.token
                            _id.value = response.body()!!.record.id
                            _user.value = it.record
                            Log.d("SingIn", token)
                            _resultState.value = ResultState.Success("Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java)
                                _resultState.value = ResultState.Error(message.toString())
                            } catch (ex: Exception) {
                                Log.e("SingIn", "Failed to parse error response: ${ex.message}")
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }
                        }

                    } catch (ex: Exception) {
                        Log.e("SingIn", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.e("SingIn", t.message.toString())
                    _resultState.value = ResultState.Error(t.message.toString())
                }
            })

        }
    }

    fun sendOTPCode(email: String) {
        viewModelScope.launch {
            _resultEmailState.value = ResultState.Loading
            apiService.OTPRequest(
                OtpRequest(email)
            ).enqueue(object : Callback<OtpResponse> {
                override fun onResponse(
                    call: Call<OtpResponse>,
                    response: Response<OtpResponse>
                ) {
                    try {
                        response.body()?.let {
                            val OtpId = it.otpId
                            _otpCode.value = it.otpId
                            Log.d("sendOtp", OtpId)
                            _resultEmailState.value = ResultState.Success("Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java)
                                _resultEmailState.value = ResultState.Error(message.toString())
                            } catch (ex: Exception) {
                                Log.e("sendOtp", "Failed to parse error response: ${ex.message}")
                                _resultEmailState.value = ResultState.Error(ex.message.toString())
                            }

                        }
                    } catch (ex: Exception) {
                        Log.e("SendOTP", ex.message.toString())
                        _resultEmailState.value = ResultState.Error(ex.message.toString())
                    }
                }

                override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                    Log.e("sendOtp", t.message.toString())
                    _resultEmailState.value = ResultState.Error(t.message.toString())
                }

            })
        }

    }

    fun sigInWithOtp(password: String, otp: String) {
        viewModelScope.launch {
            _resultState.value = ResultState.Loading
            apiService.signInWithOTP(
                OtpAuth(
                    otp,
                    password
                )
            ).enqueue(object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    try {
                        response.body()?.let {
                            val OtpId = it.token
                            _user.value = it.record
                            _token.value = it.token
                            Log.d("SingInOtp", OtpId)
                            _resultState.value = ResultState.Success("Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java)
                                _resultState.value = ResultState.Error(message.toString())
                            } catch (ex: Exception) {
                                Log.e("SingInOtp", "Failed to parse error response: ${ex.message}")
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }

                        }
                    } catch (ex: Exception) {
                        Log.e("SingInOtp", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Log.e("SingInOtp", t.message.toString())
                    _resultState.value = ResultState.Error(t.message.toString())
                }

            })
        }
    }

    fun passwordUpdate( oldPassword:String, token:String, password:String, passwordConfirm:String, userId:String){
        viewModelScope.launch {
            _resultState.value = ResultState.Loading
            apiService.changePassword(
                userId,
                token,
                ChangePassRequest(
                    oldPassword,
                    password,
                    passwordConfirm
                )
            ).enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    try {
                        response.body()?.let {
                            val OtpId = it.id
                            Log.d("PasswordUpdate", OtpId)
                            _resultState.value = ResultState.Success("Success")
                        }
                        response.errorBody()?.let {
                            try {
                                val message =
                                    Gson().fromJson(it.string(), ErrorResponce::class.java)
                                _resultState.value = ResultState.Error(message.toString())
                            } catch (ex: Exception) {
                                Log.e("PasswordUpdate", "Failed to parse error response: ${ex.message}")
                                _resultState.value = ResultState.Error(ex.message.toString())
                            }

                        }
                    } catch (ex: Exception) {
                        Log.e("PasswordUpdate", ex.message.toString())
                        _resultState.value = ResultState.Error(ex.message.toString())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("PasswordUpdate", t.message.toString())
                    _resultState.value = ResultState.Error(t.message.toString())
                }

            })
        }

    }

    fun getId(): String = _id.value

}