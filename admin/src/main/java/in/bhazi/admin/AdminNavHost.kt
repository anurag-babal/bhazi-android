package `in`.bhazi.admin

import `in`.bhazi.admin.ui.screenB
import `in`.bhazi.feature.adminhome.adminHomeScreen
import `in`.bhazi.feature.adminhome.navigateAdminHomeRoute
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AdminNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = navigateAdminHomeRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        adminHomeScreen()
        screenB()
    }
}