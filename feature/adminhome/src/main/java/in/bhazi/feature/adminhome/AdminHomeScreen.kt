package `in`.bhazi.feature.adminhome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AdminHomeRoute(

) {
    AdminHomeScreen()
}

@Composable
fun AdminHomeScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Inside Account", modifier = Modifier.align(Alignment.Center))
    }
}