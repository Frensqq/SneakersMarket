package com.example.createinlager.presentation.screen.markets

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.example.createinlager.presentation.screen.Navigate
import com.example.createinlager.presentation.theme.ui.CreateInLagerTheme

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation by mutableStateOf<Location?>(null)

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Инициализация разрешений
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getCurrentLocation()
            }
        }

        // Запрос разрешений на геолокацию
        when {
            ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                getCurrentLocation()
            }
            else -> {
                requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
            }
        }

        // Инициализация MapKit
        MapKitFactory.setApiKey("e1efc101-9cbc-4179-a7f9-866e82682797")
        MapKitFactory.initialize(this)

        setContent {
            CreateInLagerTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    MapScreen(currentLocation)
                }        }



    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                currentLocation = location
            }
    }
}

@Composable
fun MapScreen(location: Location?) {
    rememberAndInitializeMapKit().bindToLifecycleOwner()

    if (location != null) {
        val startPosition = ru.sulgik.mapkit.map.CameraPosition(
            Point(location.latitude, location.longitude),
            15.0f,
            0.0f,
            0.0f
        )
        val cameraPositionState = rememberCameraPositionState { position = startPosition }
        YandexMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        // Обработка случая, когда местоположение не доступно
        Text("Waiting for location...")
    }
}