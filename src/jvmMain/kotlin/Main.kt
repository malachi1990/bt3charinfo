import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.ScheduleGeneratorView

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!1") }
    var text2 by remember { mutableStateOf("Hello button 2!") }
    var text3 by remember { mutableStateOf("Hello, World!3") }
    var text4 by remember { mutableStateOf("Hello button 4!") }
    var text5 by remember { mutableStateOf("Hello button 5!") }

    MaterialTheme {
        ScheduleGeneratorView()
//        Row() {

//           Column(Modifier.align(Alignment.Top), Arrangement.Center, Alignment.End) {
//                Button(onClick = {
//                    text3 = "Hello, left colum1!"
//                }) {
//                    Text(text3)
//                }
//                Button(onClick = {
//                    text4 = "Hello, left colum2!"
//                }) {
//                    Text(text4)
//                }
//                Button(onClick = {
//                    text5 = "Hello, left colum5!"
//                }) {
//                    Text(text5)
//                }
//               TeamDropDown()
//            }
//            Column(Modifier.align(Alignment.CenterVertically), Arrangement.Center, Alignment.CenterHorizontally) {
//                Image(
//                    painter = painterResource("images/59.png"),
//                    contentDescription = "Sample",
//                    modifier = Modifier.defaultMinSize()
//                )
//            }
//            Column(Modifier.align(Alignment.Top), Arrangement.Center, Alignment.End) {
//                Button(onClick = {
//                    text = "Hello, Desktop!"
//                }) {
//                    Text(text)
//                }
//                Button(onClick = {
//                    text2 = "Button 2, Desktop!"
//                }) {
//                    Text(text2)
//                }
//            }

//        }
//        Row(){
//
//        }
    }


}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DBZ League Character viewer"
    ) {
        App()
//        TeamDropDown()
        ScheduleGeneratorView ()
    }
}
