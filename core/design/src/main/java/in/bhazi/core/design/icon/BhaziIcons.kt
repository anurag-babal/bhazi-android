package `in`.bhazi.core.design.icon

import `in`.bhazi.core.design.R
import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Add
import androidx.compose.ui.graphics.vector.ImageVector

object BhaziIcons {
    val Home = Icons.Outlined.Home
    val AccountCircle = Icons.Outlined.AccountCircle
    val Add = Icons.Rounded.Add
    val Remove = R.drawable.ic_baseline_remove_24
    val RemoveCircle = R.drawable.ic_baseline_remove_circle_outline_24
    val Cart = Icons.Outlined.ShoppingCart
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}