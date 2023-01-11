package `in`.bhazi.core.ui

import `in`.bhazi.android.app.navigation.TopLevelDestination
import `in`.bhazi.core.design.icon.Icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun BhaziBottomBar(
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            val interactionSource = remember { MutableInteractionSource() }
            BottomNavigationItem(
                icon = {
                    BottomBarIcon(
                        icon = destination.selectedIcon,
                        label = destination.iconTextId,
                        selected = selected
                    )
                },
                interactionSource = interactionSource,
                selected = selected,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = { onNavigateToDestination(destination) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

@Composable
fun BottomBarIcon(
    icon: Icon,
    label: Int,
    selected: Boolean = true
) {
    val borderModifier = if (selected) Modifier
        .border(
            2.dp,
            MaterialTheme.colors.secondary,
            RoundedCornerShape(16.dp)
        )
    else Modifier

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = borderModifier
            .padding(8.dp)
    ) {
        when(icon) {
            is Icon.ImageVectorIcon -> {
                Icon(imageVector = icon.imageVector, contentDescription = null)
            }
            is Icon.DrawableResourceIcon -> {
                Icon(painter = painterResource(id = icon.id), contentDescription = null)
            }
        }
        AnimatedVisibility(visible = selected) {
            if (selected) {
                Text(
                    text = stringResource(id = label),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    maxLines = 1,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}