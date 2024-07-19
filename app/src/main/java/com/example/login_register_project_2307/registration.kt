package com.example.login_register_project_2307

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login_register_project_2307.ui.theme.Login_register_project_2307Theme
import com.example.login_register_project_2307.ui.theme.Screen
import java.util.logging.Logger.global

@Composable
fun Registration(navController: NavController) {
    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var dateofbirth by rememberSaveable { mutableStateOf("") }
    var emailaddress by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier= Modifier.padding(8.dp)) {
        Text(text = "Rejestracja nowego użytkownika", fontSize = 32.sp, lineHeight = 50.sp, textAlign = TextAlign.Center)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Imię") }

        )
        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Nazwisko") }
        )
        OutlinedTextField(
            value = dateofbirth,
            onValueChange = { dateofbirth = it },
            label = { Text("Data urodzenia") }
        )
        OutlinedTextField(
            value = emailaddress,
            onValueChange = { emailaddress = it },
            label = { Text("Adres e-mail") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Utwórz hasło") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(onClick = {navController.navigate(route= Screen.Login.route)}) {
            Text("Potwierdź")
        }

    }

}




@Composable
fun ConfirmRegistration() {


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Login_register_project_2307Theme {
//        Greeting("Android")
//    }
//}
@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    Login_register_project_2307Theme {
        Registration(navController = rememberNavController())
    }
}
@Preview(showBackground = true)
@Composable
fun ButtonPreview(){
    Login_register_project_2307Theme {
        ConfirmRegistration()
    }
}
