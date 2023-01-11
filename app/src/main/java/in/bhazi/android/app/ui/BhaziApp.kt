package `in`.bhazi.android.app.ui

import `in`.bhazi.android.app.BhaziNavHost
import `in`.bhazi.android.R
import `in`.bhazi.android.core.data.util.NetworkMonitor
import `in`.bhazi.core.design.theme.BhaziTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun BhaziApp(
    networkMonitor: NetworkMonitor,
    appState: BhaziAppState = rememberBhaziAppState(
        networkMonitor = networkMonitor
    )
) {
    BhaziTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        val isOffline by appState.isOffline.collectAsState()
        val navController = appState.navController

        val notConnectedMessage = stringResource(id = R.string.not_connected)
        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                BhaziBottomBar(
                    destinations = appState.topLevelDestinations,
                    currentDestination = appState.currentDestination,
                    onNavigateToDestination = appState::navigateToTopLevelDestination
                )
            }
        ) { innerPadding ->
            BhaziNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}