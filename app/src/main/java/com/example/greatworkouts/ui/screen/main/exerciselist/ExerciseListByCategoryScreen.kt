package com.example.greatworkouts.ui.screen.main.exerciselist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.ui.components.exerciselist.ExerciseCard


@Composable
fun ExerciseByCategoryScreen(
    categoryName: String,
    goBack: () -> Unit,
    goToInstructions: (String) -> Unit,
    exerciseListViewModel: ExerciseListViewModel = viewModel(factory = ExerciseListViewModel.factory)
) {

    val context = LocalContext.current
    var showStandingExercises: Boolean by remember { mutableStateOf(false) }
    var showOnFloorExercises: Boolean by remember { mutableStateOf(false) }

    var standingExerciseList: List<Exercise> =
        exerciseListViewModel.getExerciseByCategoryToolAndStanding(
            toolName = "Body Weight",
            standing = true,
            categoryName = categoryName
        ).collectAsState(initial = emptyList()).value

    var onFloorExerciseList: List<Exercise> =
        exerciseListViewModel.getExerciseByCategoryToolAndStanding(
            toolName = "Body Weight",
            standing = false,
            categoryName = categoryName
        ).collectAsState(initial = emptyList()).value


    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(60.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { goBack() }
                ) {
                    Icon(
                        Icons.Default.ChevronLeft,
                        tint = Color.Black,
                        contentDescription = null,
                    )
                }
                Text(
                    text = categoryName,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    ) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            item() {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            showStandingExercises = !showStandingExercises
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Standing",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Icon(
                            imageVector = if (!showStandingExercises) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Back"
                        )
                    }
                }
            }
            if (showStandingExercises) {
                itemsIndexed(standingExerciseList) { index, exercise ->
                    ExerciseCard(
                        name = exercise.name,
                        shape = RoundedCornerShape(16.dp),
                        thumbnail = exercise.thumbnail,
                        context = context,
                        goToInstructions = goToInstructions,
                        borderModifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
            item() {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(60.dp)
                        .clickable {
                            showOnFloorExercises = !showOnFloorExercises
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "On the floor",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Icon(
                            imageVector = if (!showOnFloorExercises) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Back"
                        )
                    }
                }
            }

            if (showOnFloorExercises) {
                itemsIndexed(onFloorExerciseList) { index, exercise ->
                    ExerciseCard(
                        name = exercise.name,
                        shape = RoundedCornerShape(16.dp),
                        thumbnail = exercise.thumbnail,
                        context = context,
                        goToInstructions = goToInstructions,
                        borderModifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

