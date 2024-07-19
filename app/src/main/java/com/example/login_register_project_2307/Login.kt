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

@Composable
fun Login(navController: NavController) {
    var log_emailaddress by rememberSaveable { mutableStateOf("") }
    var log_password by rememberSaveable { mutableStateOf("") }
    val emailadress="mateo.goc@onet.eu"
    val password="1234"
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Logowanie",
            fontSize = 32.sp, lineHeight = 50.sp, textAlign = TextAlign.Center)
        OutlinedTextField(
            value = log_emailaddress,
            onValueChange = { log_emailaddress = it },
            label = { Text("E-mail") }
        )
        OutlinedTextField(
            value = log_password,
            onValueChange = { log_password = it },
            label = { Text("Hasło") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(onClick = { if(log_emailaddress==emailadress &&  log_password==password){navController.navigate(
            Screen.Login.route) }else{navController.popBackStack()}}) {
            Text("Zaloguj")
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Zarejestruj się")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login_register_project_2307Theme {
        Login(navController = rememberNavController())
    }
}
