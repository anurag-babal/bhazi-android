package `in`.bhazi.core.design.component

import `in`.bhazi.core.design.theme.BhaziTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(
    onClickMenuItem: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Menu",
    selectedOption: String = "",
    options: List<String> = listOf()
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            label = { Text(text = label) },
            readOnly = true,
            value = selectedOption,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            onValueChange = {  },
//            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onClickMenuItem(option)
                    expanded = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDropDownMenu() {
    BhaziTheme {
        DropDownMenu(
            selectedOption = "Ordered",
            onClickMenuItem = {  },
            modifier = Modifier.padding(8.dp)
        )
    }
}