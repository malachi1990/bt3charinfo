package view

import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.geometry.Size

@Composable
fun TeamDropDown() {

    val teamList = listOf("Androids", "Budokai", "Buu Saga", "Cinema",
        "Cold Kingdom", "Derp", "Earth Defenders", "GT",
        "Hybrids", "Kaiju", "Muscle", "Namek",
        "Resurrected Warriors", "Royals", "Rugrats", "Sentai Squad")

     var mSelectedText by remember { mutableStateOf("") }
     var selected = false
     var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

        DropdownMenu(
            expanded = false,
            onDismissRequest = { selected = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            teamList.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    selected = false
                }) {
                    Text(text = label)
                }
            }
        }

}