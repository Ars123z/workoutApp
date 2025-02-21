package com.example.greatworkouts.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greatworkouts.data.workoutCategories
import com.example.greatworkouts.ui.components.MainPlanCard
import com.example.greatworkouts.ui.components.PlanCard


@Composable
fun Plans() {
    Scaffold() { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),

            ) {
            item() {
                Column(

                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                ) {
                    Text(
                        text = "Select a fitness plan!",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "All plans are personalized for you.",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    MainPlanCard(
                        name = "Full Body Toning",
                        tag = listOf("Strength", "Endurance"),
                        coverImage = "plans/balanced_fat_loss.jpg"
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
            itemsIndexed(workoutCategories) { index, workoutCategory ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = workoutCategory.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = workoutCategory.description,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.DarkGray),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                        items(workoutCategory.workouts) { workout ->
                            PlanCard(
                                name = workout.name,
                                tag = workout.tag,
                                coverImage = workout.coverImage,
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(end = 8.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    if (index == workoutCategories.size - 1) {
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}

