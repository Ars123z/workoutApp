package com.example.greatworkouts.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.ui.components.CategoryCard

val categoryList = listOf(
    listOf("Strength", "workout_main/strength.png"),
    listOf("HIIT, Cardio", "workout_main/hiit_cardio.png"),
    listOf("Yoga, Stretching", "workout_main/yoga_stretching.png"),
    listOf("Warmup, Recovery", "workout_main/warmup_recovery.png"),
    listOf("Breathwork", "workout_main/breathwork.png")
)

@Composable
fun WorkoutMain(
    goToCategory: (String) -> Unit,
    goToMenu: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item() {
            WorkoutHeader(onMenuClick = { goToMenu() })
        }
        items(count = categoryList.size) { item ->
            CategoryCard(name = categoryList[item][0], path = categoryList[item][1], goToCategory = goToCategory)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun WorkoutHeader(
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Workouts",
            style = MaterialTheme.typography.headlineMedium
        )
        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu"
            )
        }
    }
}
