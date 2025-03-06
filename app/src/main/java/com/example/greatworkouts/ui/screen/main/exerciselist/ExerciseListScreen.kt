package com.example.greatworkouts.ui.screen.main.exerciselist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.R
import com.example.greatworkouts.ui.screen.main.exercise.ExerciseViewModel


@Composable
fun ExerciseList(
    goToInstructions: (String) -> Unit,
    goBack: () -> Unit,
    goToCategory: (String) -> Unit,
    goToTool: (String) -> Unit,
    exerciseViewModel: ExerciseViewModel = viewModel(factory = ExerciseViewModel.Companion.Factory)
) {
    val containerColor = Color(0xFFCCCCCC)

    var categoryName by remember { mutableStateOf<List<String>?>(null) }
    var toolName by remember { mutableStateOf<List<String>?>(null) }
    var difficulty by remember { mutableStateOf<List<String>?>(null) }
    var standing by remember { mutableStateOf<Boolean?>(null) }
    var noise by remember { mutableStateOf<Boolean?>(null) }
    var searchText by remember { mutableStateOf<String>("") }

    var searchView: Boolean = searchText.isNotEmpty()
    var filterView: Boolean by remember { mutableStateOf(false) }
    var currentView: String by remember { mutableStateOf("Main") }

    var categoryList = exerciseViewModel.getAllCategories().collectAsState(initial = listOf()).value
    var fitnessTools = exerciseViewModel.getAllTools().collectAsState(initial = listOf()).value

    val exerciseList = if (difficulty == null) {
        exerciseViewModel.getExerciseList(
            searchText = searchText,
            categoryName = categoryName,
            toolName = toolName,
            standing = standing,
            noise = noise
        ).collectAsState(initial = listOf()).value
    } else {
        exerciseViewModel.getExerciseList(
            searchText = searchText,
            categoryName = categoryName,
            toolName = toolName,
            standing = standing,
            difficulty = difficulty!!,
            noise = noise
        ).collectAsState(initial = listOf()).value
    }

    LaunchedEffect(searchView, filterView) {
        currentView = when {
            searchView -> "Search"
            filterView -> "Filter"
            else -> "Main"
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
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
                        text = "Exercises",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    IconButton(
                        onClick = { filterView = !filterView }
                    ) {
                        Icon(
                            Icons.Filled.FilterList,
                            tint = Color.Black,
                            contentDescription = null,
                        )
                    }

                }
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = containerColor,
                        unfocusedContainerColor = containerColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    placeholder = {
                        Text(
                            text = "Search",
                            color = Color.Gray
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(32.dp))
            }
        }
    ) { padding ->
        when (currentView) {
            "Main" -> Main(
                categoryList = categoryList,
                fitnessTools = fitnessTools,
                borderColor = containerColor,
                padding = padding,
                goToCategory = goToCategory,
                goToTool = goToTool
            )

            "Search" -> Search(
                exerciseList = exerciseList,
                padding = padding,
                borderColor = containerColor,
                goToInstructions = goToInstructions
            )

            "Filter" -> FilterView(
                addToTool = {
                    toolName =
                        if (toolName == null) {
                            listOf(it)
                        } else {
                            toolName!!.plus(it)
                        }
                },
                addToCategories = {
                    categoryName =
                        if (categoryName == null) {
                            listOf(it)
                        } else {
                            categoryName!!.plus(it)
                        }
                },
                selectedCategories = categoryName ?: emptyList<String>(),
                selectedTools = toolName ?: emptyList<String>(),

                changeView = { currentView = it },
                padding = padding
            )

            else -> Main(
                categoryList = categoryList,
                fitnessTools = fitnessTools,
                borderColor = containerColor,
                padding = padding,
                goToCategory = goToCategory,
                goToTool = goToTool
            )
        }
    }
}


fun nameToDrawable(name: String): Int {
    return when (name) {
        "Abs & Core" -> R.drawable.abs_core
        "Upper Body" -> R.drawable.upper_body
        "Lower Body" -> R.drawable.lower_body
        "Cardio" -> R.drawable.cardio
        "Stretching" -> R.drawable.stretching
        "Yoga" -> R.drawable.yoga
        "BOSU" -> R.drawable.bosu
        "Barbell" -> R.drawable.barbell
        "Dumbbell" -> R.drawable.dumbbell
        "Foam Roller" -> R.drawable.foam_roller
        "Swiss Ball" -> R.drawable.swissball
        "Kettlebell" -> R.drawable.kettlebell
        "Medicine Ball" -> R.drawable.medicine_ball
        "Pull Up Bar" -> R.drawable.pullup_bar
        "Resistance Band" -> R.drawable.resistance_band
        "Suspension System" -> R.drawable.suspension_system
        else -> {
            R.drawable.medicine_ball
        }
    }
}

