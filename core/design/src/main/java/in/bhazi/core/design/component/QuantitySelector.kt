package `in`.bhazi.core.design.component

import `in`.bhazi.core.design.R
import `in`.bhazi.core.design.icon.BhaziIcons
import `in`.bhazi.core.design.icon.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuantitySelector(
    quantity: Byte,
    onClickIncrement: () -> Unit,
    onClickDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "Qty",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterVertically)
        )
        TintedIconButton(
            icon = Icon.DrawableResourceIcon(BhaziIcons.Remove),
            contentDescription = stringResource(id = R.string.decrease),
            onClick = onClickDecrement
        )
        Text(
            text = "$quantity",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(min = 24.dp)
        )
        TintedIconButton(
            icon = Icon.ImageVectorIcon(BhaziIcons.Add),
            contentDescription = stringResource(id = R.string.increase),
            onClick = onClickIncrement
        )
    }
}
