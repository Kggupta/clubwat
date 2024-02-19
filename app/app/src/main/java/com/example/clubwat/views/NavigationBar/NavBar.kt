package com.example.clubwat.views.NavigationBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun NavBar(navController: NavController) {
    val items = listOf(
        NavBarItem.Home,
        NavBarItem.ForYou,
        NavBarItem.Search,
        NavBarItem.Profile
    )

    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item,
                name = item.title,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavBarItem,
    name: String,
    navController: NavController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title
            )
        },
        selected = true,
        alwaysShowLabel = true,
        onClick = {
            if (name == "Home") { navController.navigate("home") }
            if (name == "For You") { navController.navigate("forYou") }
            if (name == "Search") { navController.navigate("home") }
            if (name == "Profile") { navController.navigate("home") }
        },
        colors = NavigationBarItemDefaults.colors()
    )
}