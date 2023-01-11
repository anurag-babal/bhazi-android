package `in`.bhazi.core.ui

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
    appState: BhaziAppState = rememberBhaziAppState()
) {
    BhaziTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        val navController = appState.navController

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