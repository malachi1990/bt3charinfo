package view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import model.schedule.Division
import model.schedule.Team
import service.schedule.ScheduleGenerator


@Composable
fun scheduleGeneratorView () {

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


    val northKaiSlots = listOf(northKaiSlot1, northKaiSlot2, northKaiSlot3, northKaiSlot4)
    val eastKaiSlots = listOf(eastKaiSlot1, eastKaiSlot2, eastKaiSlot3, eastKaiSlot4)
    val westKaiSlots = listOf(westKaiSlot1, westKaiSlot2, westKaiSlot3, westKaiSlot4)
    val southKaiSlots = listOf(southKaiSlot1, southKaiSlot2, southKaiSlot3, southKaiSlot4)

    val focusRequester = remember { FocusRequester() }

    Row {
        Column {
            Text("North Kai Teams")

            Box {
                OutlinedTextField(
                    value = northKaiSlot1,
                    onValueChange = {
                        northKaiSlot1 = it
                    },
                    placeholder = { Text("North Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = northKaiSlot2,
                    onValueChange = {
                        northKaiSlot2 = it
                    },
                    placeholder = { Text("North Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = northKaiSlot3,
                    onValueChange = {
                        northKaiSlot3 = it

                    },
                    placeholder = { Text("North Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = northKaiSlot4,
                    onValueChange = {
                        northKaiSlot4 = it
                    },
                    placeholder = { Text("North Kai Team") }
                )
            }
        }
        Column {
            Box {
                Text("East Kai Teams")
            }
            Box {
                OutlinedTextField(
                    value = eastKaiSlot1,
                    onValueChange = {
                        eastKaiSlot1 = it
                    },
                    placeholder = { Text("East Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = eastKaiSlot2,
                    onValueChange = {
                        eastKaiSlot2 = it
                    },
                    placeholder = { Text("East Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = eastKaiSlot3,
                    onValueChange = {
                        eastKaiSlot3 = it
                    },
                    placeholder = { Text("East Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = eastKaiSlot4,
                    onValueChange = {
                        eastKaiSlot4 = it
                    },
                    placeholder = { Text("East Kai Team") }
                )
            }
        }
        Column {
            Box {
                Text("West Kai Teams")
            }
            Box {
                OutlinedTextField(
                    value = westKaiSlot1,
                    onValueChange = {
                        westKaiSlot1 = it
                    },
                    placeholder = { Text("West Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = westKaiSlot2,
                    onValueChange = {
                        westKaiSlot2 = it
                    },
                    placeholder = { Text("West Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = westKaiSlot3,
                    onValueChange = {
                        westKaiSlot3 = it
                    },
                    placeholder = { Text("West Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = westKaiSlot4,
                    onValueChange = {
                        westKaiSlot4 = it
                    },
                    placeholder = { Text("West Kai Team") }
                )
            }
        }
        Column {
            Box {
                Text("South Kai Teams")
            }
            Box {
                OutlinedTextField(
                    value = southKaiSlot1,
                    onValueChange = {
                        southKaiSlot1 = it

                    },
                    placeholder = { Text("South Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = southKaiSlot2,
                    onValueChange = {
                        southKaiSlot2 = it
                    },
                    placeholder = { Text("South Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = southKaiSlot3,
                    onValueChange = {
                        southKaiSlot3 = it
                    },
                    placeholder = { Text("South Kai Team") }
                )
            }
            Box {
                OutlinedTextField(
                    value = southKaiSlot4,
                    onValueChange = {
                        southKaiSlot4 = it
                    },
                    placeholder = { Text("South Kai Team") }
                )
            }

        }
        Column {
            Text("Generate Schedule here")


            Box {
                Button(
                    onClick = {
                        ScheduleGenerator().generateSchedule(
                            buildTeamFromTextFieldList(northKaiSlots, Division.NORTH_KAI),
                            buildTeamFromTextFieldList(eastKaiSlots, Division.EAST_KAI),
                            buildTeamFromTextFieldList(westKaiSlots, Division.WEST_KAI),
                            buildTeamFromTextFieldList(southKaiSlots, Division.SOUTH_KAI)
                        )
                    },
                    modifier = Modifier.focusRequester(focusRequester),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "Generate Schedule")
                }
            }
        }

    }

}


fun buildTeamFromTextFieldList(teamNames : List<TextFieldValue>, division: Division) : MutableList<Team> {
    val teams = mutableListOf<Team>()
    teamNames.forEach{team -> teams.add(Team(team.text, division, mutableListOf())) }
    return teams
}

