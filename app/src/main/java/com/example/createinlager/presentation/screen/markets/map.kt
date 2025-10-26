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
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.example.createinlager.R
import com.example.createinlager.presentation.screen.buttonBack
import com.example.createinlager.presentation.theme.ui.CreateInLagerTheme
import com.example.createinlager.presentation.theme.ui.TitleCategoryType
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.yandex.mapkit.MapKitFactory
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.location.LocationListener
import ru.sulgik.mapkit.location.LocationStatus


class MainActivityTwo : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLocation by mutableStateOf<Location?>(null)

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MapKitFactory.setApiKey("e1efc101-9cbc-4179-a7f9-866e82682797")
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

        MapKitFactory.initialize(this)

        setContent {
            CreateInLagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    val context = LocalContext.current

                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                            Spacer(modifier = Modifier.height(48.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(RoundedCornerShape(40.dp))
                                        .background(colorResource(R.color.hint)),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    IconButton(
                                        onClick = {
                                            finish()
                                        },
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(colorResource(R.color.background))
                                    ) {
                                        Icon(
                                            bitmap = ImageBitmap.imageResource(R.drawable.back),
                                            contentDescription = "Назад",
                                            modifier = Modifier.fillMaxWidth(),
                                            tint = colorResource(R.color.text)
                                        )
                                    }
                                }

                                Text(
                                    text = "Карта",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TitleCategoryType,
                                    textAlign = TextAlign.Center
                                )
                            }

                            Box(modifier = Modifier.padding(top = 25.dp, bottom = 42.dp).clip(RoundedCornerShape(10.dp))) {

                                MapScreen(currentLocation)
                            }
                        }
                    }

                }
            }
        }
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