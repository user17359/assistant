package com.example.assistant.elements

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assistant.AssistantScreen
import com.example.assistant.R

@Composable
fun BottomNavBar(selected: Int, navController: NavController) {

    val iconSize = 36.dp
    val offset = (-10).dp
    val navBarItemColors = NavigationBarItemDefaults.colors(
        unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
        unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        indicatorColor = MaterialTheme.colorScheme.primaryContainer
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        NavigationBarItem(
            selected = selected == 0,
            onClick = { navController.navigate(AssistantScreen.GameScreen.name) },
            label = { Text(text = "Gry", fontSize = 13.sp) },
            icon = {
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .offset(y = offset),
                    painter = painterResource(id = R.drawable.quiz),
                    contentDescription = "Quiz",
                )
            },
            colors = navBarItemColors
        )
        NavigationBarItem(
            selected = selected == 1,
            onClick = { navController.navigate(AssistantScreen.CalendarScreen.name) },
            label = { Text("Kalendarz", fontSize = 13.sp) },
            icon = {
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .offset(y = offset),
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "Calendar"
                )
            },
            colors = navBarItemColors
        )
        NavigationBarItem(
            selected = selected == 2,
            onClick = { navController.navigate(AssistantScreen.ChatScreen.name) },
            label = { Text("Czat", fontSize = 13.sp) },
            icon = {
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .offset(y = offset),
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            },
            colors = navBarItemColors
        )
        NavigationBarItem(
            selected = selected == 3,
            onClick = { navController.navigate(AssistantScreen.ExerciseScreen.name) },
            label = { Text("Ćwiczenia", fontSize = 13.sp) },
            icon = {
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .offset(y = offset),
                    painter = painterResource(id = R.drawable.excercise),
                    contentDescription = "Exercise"
                )
            },
            colors = navBarItemColors
        )
        NavigationBarItem(
            selected = selected == 4,
            onClick = { navController.navigate(AssistantScreen.ProfileScreen.name) },
            label = { Text("Profil", fontSize = 13.sp) },
            icon = {
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .offset(y = offset),
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile"
                )
            },
            colors = navBarItemColors
        )
    }
}

@Preview
@Composable
fun PreviewBottomNavBar() {
    BottomNavBar(3, rememberNavController())
}