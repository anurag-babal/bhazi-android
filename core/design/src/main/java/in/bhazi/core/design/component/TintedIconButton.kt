package `in`.bhazi.core.design.component

import `in`.bhazi.core.design.icon.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TintedIconButton(
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Icon
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val border = Modifier.border(
        width = 2.dp,
        color = MaterialTheme.colors.onSurface,
        shape = CircleShape
    )

    val colors = listOf(
        MaterialTheme.colors.primary,
        MaterialTheme.colors.primaryVariant
    )
    val color = MaterialTheme.colors.primaryVariant
    Surface(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .clip(CircleShape)
            .then(border)
    ) {
        when(icon) {
            is Icon.ImageVectorIcon -> {
                Icon(
                    imageVector = icon.imageVector,
                    contentDescription = contentDescription,
                    modifier = Modifier.drawWithContent {
                        drawContent()
                        drawCircle(
                            color = color,
                            blendMode = BlendMode.Plus
                        )
                    }
                )
            }
            is Icon.DrawableResourceIcon -> {
                Icon(
                    painter = painterResource(id = icon.id),
                    contentDescription = contentDescription,
                    modifier = Modifier.drawWithContent {
                        drawContent()
                        drawCircle(
                            color = color,
                            blendMode = BlendMode.Plus
                        )
                    }
                )
            }
        }
    }
}
