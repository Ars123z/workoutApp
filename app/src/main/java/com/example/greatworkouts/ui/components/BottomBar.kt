package com.example.greatworkouts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.greatworkouts.ui.navigation.Screens

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val bottomBarScreens = listOf(
        Screens.Plan,
        Screens.WorkoutScreen,
        Screens.Food,
        Screens.Dashboard,
        Screens.Profile
    )
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route

    val current: Screens = Screens.fromRoute(currentScreen)

    if (bottomBarScreens.contains(current)) {
        NavigationBar(
            modifier = Modifier.height(50.dp)
                .padding(vertical= 0.dp),
            containerColor = Color.White,
        ) {
            bottomBarScreens.forEach { screen ->
                NavigationBarItem(
                    modifier = Modifier.padding(vertical= 0.dp)
                        .height(50.dp),
                    icon = {
                        if (screen.icon != null) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(screen.icon),
                                    contentDescription = screen.title,
                                    tint = if (currentScreen == screen.route) MaterialTheme.colorScheme.primary else Color.Gray,
                                    modifier = Modifier.size(if (currentScreen == screen.route) 30.dp else 25.dp)
                                        .padding(horizontal = 0.dp)
                                )
                                Text(
                                    text = screen.title,
                                    color = if (currentScreen == screen.route) MaterialTheme.colorScheme.primary else Color.Gray,
                                    style = if (currentScreen == screen.route) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(0.dp)
                                )
                            }
                        }
                    },
                    selected = currentScreen == screen.route,
                    onClick = { navController.navigate(screen.route) },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )

                )
            }
        }
    }
}