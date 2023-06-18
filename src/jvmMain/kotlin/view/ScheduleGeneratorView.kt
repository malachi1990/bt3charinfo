package view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.toSize
import model.schedule.Division
import model.schedule.Team
import service.schedule.ScheduleGenerator


@Composable
fun ScheduleGeneratorView () {
    var northKaiSlot1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var northKaiSlot2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var northKaiSlot3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var northKaiSlot4 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

    var eastKaiSlot1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var eastKaiSlot2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var eastKaiSlot3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var eastKaiSlot4 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

    var westKaiSlot1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var westKaiSlot2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var westKaiSlot3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var westKaiSlot4 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

    var southKaiSlot1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var southKaiSlot2 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var southKaiSlot3 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
    var southKaiSlot4 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }


    Row() {
        Column() {
            Box() {
                OutlinedTextField(
                    value = northKaiSlot1,
                    onValueChange = {
                        northKaiSlot1 = it
                    },
                    label = {Text("North Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = northKaiSlot2,
                    onValueChange = {
                        northKaiSlot2 = it
                    },
                    label = {Text("North Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = northKaiSlot3,
                    onValueChange = {
                        northKaiSlot3 = it
                    },
                    label = {Text("North Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = northKaiSlot4,
                    onValueChange = {
                        northKaiSlot4 = it
                    },
                    label = {Text("North Kai Team")}
                )
            }
        }
        Column() {
            Box() {
                OutlinedTextField(
                    value = eastKaiSlot1,
                    onValueChange = {
                        eastKaiSlot1 = it
                    },
                    label = {Text("East Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = eastKaiSlot2,
                    onValueChange = {
                        eastKaiSlot2 = it
                    },
                    label = {Text("East Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = eastKaiSlot3,
                    onValueChange = {
                        eastKaiSlot3 = it
                    },
                    label = {Text("East Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = eastKaiSlot4,
                    onValueChange = {
                        eastKaiSlot4 = it
                    },
                    label = {Text("East Kai Team")}
                )
            }
        }
        Column() {
            Box() {
                OutlinedTextField(
                    value = westKaiSlot1,
                    onValueChange = {
                        westKaiSlot1 = it
                    },
                    label = {Text("West Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = westKaiSlot2,
                    onValueChange = {
                        westKaiSlot2 = it
                    },
                    label = {Text("West Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = westKaiSlot3,
                    onValueChange = {
                        westKaiSlot3 = it
                    },
                    label = {Text("West Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = westKaiSlot4,
                    onValueChange = {
                        westKaiSlot4 = it
                    },
                    label = {Text("West Kai Team")}
                )
            }
        }
        Column() {
            Box() {
                OutlinedTextField(
                    value = southKaiSlot1,
                    onValueChange = {
                        southKaiSlot1 = it
                    },
                    label = {Text("South Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = southKaiSlot2,
                    onValueChange = {
                        southKaiSlot2 = it
                    },
                    label = {Text("South Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = southKaiSlot3,
                    onValueChange = {
                        southKaiSlot3 = it
                    },
                    label = {Text("South Kai Team")}
                )
            }
            Box() {
                OutlinedTextField(
                    value = southKaiSlot4,
                    onValueChange = {
                        southKaiSlot4 = it
                    },
                    label = {Text("South Kai Team")}
                )
            }

        }
        Column() {
            Box() {
                Button(
                    onClick = {

                        ScheduleGenerator().
                            generateSchedule(
                                buildTeamObject(listOf(northKaiSlot1.text, northKaiSlot2.text, northKaiSlot3.text, northKaiSlot4.text), Division.NORTH_KAI),
                                buildTeamObject(listOf(eastKaiSlot1.text, eastKaiSlot2.text, eastKaiSlot3.text, eastKaiSlot4.text), Division.EAST_KAI),
                                buildTeamObject(listOf(westKaiSlot1.text, westKaiSlot2.text, westKaiSlot3.text, westKaiSlot4.text), Division.WEST_KAI),
                                buildTeamObject(listOf(southKaiSlot1.text, southKaiSlot2.text, southKaiSlot3.text, southKaiSlot4.text), Division.SOUTH_KAI))
                    },
                    shape = MaterialTheme.shapes.medium
                ){
                    Text(text = "Generate Schedule")
                }
            }
        }

    }
}

fun buildTeamObject(teamNames : List<String>, division: Division) : MutableList<Team>{
    val teams = mutableListOf<Team>()
    teamNames.forEach{team -> teams.add(Team(team, division, mutableListOf()))}
    return teams
}

@Composable
fun getTextSaver() : String {
    val slot by remember { mutableStateOf("") }
    return slot
}

fun buildTeamFromTextFieldList(teamNames : List<TextFieldValue>, division: Division) : MutableList<Team> {
    val teams = mutableListOf<Team>()
    teamNames.forEach{team -> teams.add(Team(team.text, division, mutableListOf())) }
    return teams
}

