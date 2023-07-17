package view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun benchCheckView() {
    var firstChar by remember { mutableStateOf("") }
    var secondChar by remember {  mutableStateOf("") }
    var thirdChar by remember {  mutableStateOf("") }
    var fourthChar by remember {  mutableStateOf("") }
    var fifthChar by remember {  mutableStateOf("") }
    var freeAgent by remember {  mutableStateOf("") }

    var week1Bench by remember {  mutableStateOf("") }
    var week1FaBench by remember {  mutableStateOf("") }

    var week2Bench by remember {  mutableStateOf("") }
    var week2FaBench by remember {  mutableStateOf("") }

    var week3Bench by remember {  mutableStateOf("") }
    var week3FaBench by remember {  mutableStateOf("") }

    var week4Bench by remember {  mutableStateOf("") }
    var week4FaBench by remember {  mutableStateOf("") }

    var week5Bench by remember {  mutableStateOf("") }
    var week5FaBench by remember {  mutableStateOf("") }

    var week6Bench by remember {  mutableStateOf("") }
    var week6FaBench by remember {  mutableStateOf("") }

    var week7Bench by remember {  mutableStateOf("") }
    var week7FaBench by remember {  mutableStateOf("") }

    var week8Bench by remember {  mutableStateOf("") }
    var week8FaBench by remember {  mutableStateOf("") }

    var week9Bench by remember {  mutableStateOf("") }
    var week9FaBench by remember {  mutableStateOf("") }

    var week10Bench by remember {  mutableStateOf("") }
    var week10FaBench by remember {  mutableStateOf("") }

    var week11Bench by remember {  mutableStateOf("") }
    var week11FaBench by remember {  mutableStateOf("") }

    var week12Bench by remember {  mutableStateOf("") }
    var week12FaBench by remember {  mutableStateOf("") }

    var week13Bench by remember {  mutableStateOf("") }
    var week13FaBench by remember {  mutableStateOf("") }

    var week14Bench by remember {  mutableStateOf("") }
    var week14FaBench by remember {  mutableStateOf("") }

    var week15Bench by remember {  mutableStateOf("") }
    var week15FaBench by remember {  mutableStateOf("") }

    Row() {
        Column() {
            Box {
                OutlinedTextField(
                    value = firstChar,
                    onValueChange = {
                        firstChar = it
                    },
                    placeholder = { Text("First main roster Character") }
                )
            }
            Box {
                OutlinedTextField(
                    value = secondChar,
                    onValueChange = {
                        secondChar = it
                    },
                    placeholder = { Text("Second main roster character") }
                )
            }
            Box {
                OutlinedTextField(
                    value = thirdChar,
                    onValueChange = {
                        thirdChar = it
                    },
                    placeholder = { Text("Third main roster character") }
                )
            }
            Box {
                OutlinedTextField(
                    value = fourthChar,
                    onValueChange = {
                        fourthChar = it
                    },
                    placeholder = { Text("Fourth main roster character") }
                )
            }
            Box {
                OutlinedTextField(
                    value = fifthChar,
                    onValueChange = {
                        fifthChar = it
                    },
                    placeholder = { Text("Fifth main roster character") }
                )
            }
            Box {
                OutlinedTextField(
                    value = freeAgent,
                    onValueChange = {
                        freeAgent = it
                    },
                    placeholder = { Text("Free Agent") }
                )
            }
        }
        Column() {
            Box {
                OutlinedTextField(
                    value = week1Bench,
                    onValueChange = {
                        week1Bench = it
                    },
                    placeholder = { Text("Week 1 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week2Bench,
                    onValueChange = {
                        week2Bench = it
                    },
                    placeholder = { Text("Week 2 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week3Bench,
                    onValueChange = {
                        week3Bench = it
                    },
                    placeholder = { Text("Week 3 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week4Bench,
                    onValueChange = {
                        week4Bench = it
                    },
                    placeholder = { Text("Week 4 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week5Bench,
                    onValueChange = {
                        week5Bench = it
                    },
                    placeholder = { Text("Week 5 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week6Bench,
                    onValueChange = {
                        week6Bench = it
                    },
                    placeholder = { Text("Week 6 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week7Bench,
                    onValueChange = {
                        week7Bench = it
                    },
                    placeholder = { Text("Week 7 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week8Bench,
                    onValueChange = {
                        week8Bench = it
                    },
                    placeholder = { Text("Week 8 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week9Bench,
                    onValueChange = {
                        week9Bench = it
                    },
                    placeholder = { Text("Week 9 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week10Bench,
                    onValueChange = {
                        week10Bench = it
                    },
                    placeholder = { Text("Week 10 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week11Bench,
                    onValueChange = {
                        week11Bench = it
                    },
                    placeholder = { Text("Week 11 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week12Bench,
                    onValueChange = {
                        week12Bench = it
                    },
                    placeholder = { Text("Week 12 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week13Bench,
                    onValueChange = {
                        week13Bench = it
                    },
                    placeholder = { Text("Week 13 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week14Bench,
                    onValueChange = {
                        week14Bench = it
                    },
                    placeholder = { Text("Week 14 weekly bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week15Bench,
                    onValueChange = {
                        week15Bench = it
                    },
                    placeholder = { Text("Week 15 weekly bench") }
                )
            }
        }

        Column() {
            Box {
                OutlinedTextField(
                    value = week1FaBench,
                    onValueChange = {
                        week1FaBench = it
                    },
                    placeholder = { Text("Week 1 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week2FaBench,
                    onValueChange = {
                        week2FaBench = it
                    },
                    placeholder = { Text("Week 2 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week3FaBench,
                    onValueChange = {
                        week3FaBench = it
                    },
                    placeholder = { Text("Week 3 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week4FaBench,
                    onValueChange = {
                        week4FaBench = it
                    },
                    placeholder = { Text("Week 4 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week5FaBench,
                    onValueChange = {
                        week5FaBench = it
                    },
                    placeholder = { Text("Week 5 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week6FaBench,
                    onValueChange = {
                        week6FaBench = it
                    },
                    placeholder = { Text("Week 6 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week7FaBench,
                    onValueChange = {
                        week7FaBench = it
                    },
                    placeholder = { Text("Week 7 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week8FaBench,
                    onValueChange = {
                        week8FaBench = it
                    },
                    placeholder = { Text("Week 8 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week9FaBench,
                    onValueChange = {
                        week9FaBench = it
                    },
                    placeholder = { Text("Week 9 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week10FaBench,
                    onValueChange = {
                        week10FaBench = it
                    },
                    placeholder = { Text("Week 10 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week11FaBench,
                    onValueChange = {
                        week11FaBench = it
                    },
                    placeholder = { Text("Week 11 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week12FaBench,
                    onValueChange = {
                        week12FaBench = it
                    },
                    placeholder = { Text("Week 12 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week13FaBench,
                    onValueChange = {
                        week13FaBench = it
                    },
                    placeholder = { Text("Week 13 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week14FaBench,
                    onValueChange = {
                        week14FaBench = it
                    },
                    placeholder = { Text("Week 14 FA bench") }
                )
            }
            Box {
                OutlinedTextField(
                    value = week15FaBench,
                    onValueChange = {
                        week15FaBench = it
                    },
                    placeholder = { Text("Week 15 FA bench") }
                )
            }
        }

        Column() {
            Box {
                Button(
                    onClick = {

                    },
//                modifier = Modifier.focusRequester(focusRequester),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "Check benches")
                }
            }
        }
    }
}