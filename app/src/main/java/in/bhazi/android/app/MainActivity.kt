package `in`.bhazi.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import `in`.bhazi.android.app.ui.BhaziApp
import `in`.bhazi.android.core.data.util.NetworkMonitor
import `in`.bhazi.core.design.theme.BhaziTheme
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var networkMonitor: NetworkMonitor

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }
                    .collect()
            }
        }
        setContent {
            val darkTheme = shouldUseDarkTheme(uiState)
            `in`.bhazi.core.design.theme.BhaziTheme(
                darkTheme = darkTheme
            ) {
                BhaziApp(networkMonitor)
            }
        }
    }
}

@Composable
fun shouldUseDarkTheme(
    uiState: MainActivityUiState
): Boolean = when(uiState) {
    is MainActivityUiState.Loading -> isSystemInDarkTheme()
    is MainActivityUiState.Success -> isSystemInDarkTheme()
}