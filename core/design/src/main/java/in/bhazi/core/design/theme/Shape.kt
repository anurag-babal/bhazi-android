package `in`.bhazi.core.design.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val BhaziShapes = Shapes(
    small = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp),
    medium = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
    large = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp)
)