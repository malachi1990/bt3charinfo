package view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize

@Composable
fun TeamDropDown() {

    val teamList = listOf("Androids", "Budokai", "Buu Saga", "Cinema",
        "Cold Kingdom", "Derp", "Earth Defenders", "GT",
        "Hybrids", "Kaiju", "Muscle", "Namek",
        "Resurrected Warriors", "Royals", "Rugrats", "Sentai Squad")

     var mSelectedText by remember { mutableStateOf("") }
     var selected = false
     var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Item1","Item2","Item3")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon =  Icons.Filled.ArrowDropDown

    Box() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Teams")},
            trailingIcon = {
                Icon(icon,"List of Teams",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            teamList.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    selected = true

                }) {
                    Text(text = label)
                }
            }
        }
    }

    Box() {
        DropdownMenu(
            expanded = true,
            onDismissRequest = { selected = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
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
}