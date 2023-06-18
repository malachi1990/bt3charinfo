package view

import androidx.compose.runtime.*
import model.MasterList

@Composable
fun BenchCheckView(roster : MasterList, faList : List<String>) {
    var firstChar by remember { mutableStateOf(roster.name) }
    var secondChar by remember { mutableStateOf(roster.name) }
    var thirdChar by remember { mutableStateOf(roster.name) }
    var fourthChar by remember { mutableStateOf(roster.name) }
    var fifthChar by remember { mutableStateOf(roster.name) }
    var freeAgent by remember { mutableStateOf(faList.first()) }



}