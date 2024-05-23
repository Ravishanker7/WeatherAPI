package com.example.weatherapi2.Currentlocation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@Composable
fun GetCurrentLocation(): Location? {
    val context = LocalContext.current
    var currentLocation by remember { mutableStateOf<Location?>(null) }
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            currentLocation = location
        }
    }

    return currentLocation
}