package com.example.weatherapi2.Currentlocation

import android.Manifest
import androidx.compose.runtime.*
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermission(onPermissionGranted: () -> Unit) {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(key1 = Unit) {
        permissionsState.launchMultiplePermissionRequest()
    }

    when {
        permissionsState.allPermissionsGranted -> {
            onPermissionGranted()
        }
        permissionsState.shouldShowRationale-> {
            // Show rationale and request permission again
        }
        !permissionsState.permissionRequested-> {
            // Permissions not granted, show UI to explain why the app needs permissions
        }
    }
}