package com.example.greatworkouts.ui.screen.main.exerciselist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.ui.components.exerciselist.CategorySection

@Composable
fun FilterView(
    addToTool: (String) -> Unit,
    addToCategories: (String) -> Unit,
    selectedCategories: List<String>,
    selectedTools: List<String>,
    changeView: (String) -> Unit,
    padding: PaddingValues
) {
    val categories = listOf(
        "Abs & Core",
        "Upper Body",
        "Lower Body",
        "Cardio",
        "Stretching",
        "Yoga"
    )
    val tools = listOf(
        "BOSU",
        "Barbell",
        "Body Weight",
        "Dumbbell",
        "Foam Roller",
        "Kettlebell",
        "Medicine Ball",
        "Pull Up Bar",
        "Resistance Band",
        "Suspension System",
        "Swiss Ball"
    )

    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        item() {
            CategorySection("Category", categories, selectedCategories) { addToCategories(it) }
        }
        item() {
            CategorySection("Fitness Tool", tools, selectedTools) { addToTool(it) }
        }
        item() {
            Text(
                text = "Tool",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray
            )
        }
        item() {
            Text(
                text = "Tool",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray
            )
        }
        item() {
            Text(
                text = "Tool",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray
            )
            Button(
                onClick = { changeView("Search") }
            ) {
                Text("Done")
            }
        }
    }
}