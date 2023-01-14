package `in`.bhazi.core.design.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MyGoogleMap(
    modifier: Modifier = Modifier,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    title: String = "Address"
) {
    val address = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(address, 10f)
    }

    LaunchedEffect(key1 = address) {
        val cameraPosition = CameraPosition.fromLatLngZoom(address, 10f)
        var update = CameraUpdateFactory.newCameraPosition(cameraPosition)
        cameraPositionState.animate(update = update)

        update = CameraUpdateFactory.zoomTo(15f)
        cameraPositionState.animate(update = update, durationMs = 2000)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings()
            .copy(
                zoomControlsEnabled = true,
                myLocationButtonEnabled = true
            )
    ) {
        Marker(
            state = MarkerState(position = address),
            title = title
        )
    }
}