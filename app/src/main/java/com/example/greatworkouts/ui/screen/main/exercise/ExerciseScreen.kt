package com.example.greatworkouts.ui.screen.main.exercise

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.ui.components.ExerciseProgressBar
import com.example.greatworkouts.ui.screen.main.workout.WorkoutViewModel
import com.example.greatworkouts.utils.FullScreenVideoPlayer
import com.example.greatworkouts.utils.getVideoUriFromAssets
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ExerciseScreen(
    name: String? = null,
    workoutViewModel: WorkoutViewModel = viewModel(factory = WorkoutViewModel.Companion.Factory),
    onExerciseCompleted: () -> Unit,
    goToInstructions: (String) -> Unit,
    goToWorkout: () -> Unit,
    goToExerciseList: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val workout = workoutViewModel.getWorkoutsByName(name!!).collectAsState(initial = null)

    val exercises = workout.value?.let {
        workoutViewModel.getExercisesByWorkout(it.name)
            .collectAsState(initial = emptyList()).value
    } ?: emptyList()

    val workoutDuration by remember(exercises) { mutableIntStateOf(exercises.sumOf { it.duration }) }
    val totalExercises = exercises.size
    var currentPosition by remember { mutableIntStateOf(1) }
    var isResting by remember { mutableStateOf(false) }
    var readyState by remember { mutableStateOf(true) }


    if (exercises.isNotEmpty()) {
        val currentExercise by remember(currentPosition) {
            mutableStateOf(exercises[currentPosition - 1])
        }

        LaunchedEffect(Unit) {
            delay(3000L)
            readyState = false
        }


        LaunchedEffect(currentPosition, isResting) {
            if (isResting) {
                delay(25000L) // 25 seconds rest
                isResting = false
                if (currentPosition < totalExercises) {
                    currentPosition++
                    readyState = true
                    Log.d("Ready State", readyState.toString())
                    coroutineScope.launch {
                        delay(3000L)
                        readyState = false
                        Log.d("Ready State", "False")
                    }
                } else {
                    onExerciseCompleted()
                }
            } else {
                delay(currentExercise.duration * 1000L)
                if ((currentPosition) % 5 == 0 && currentPosition < totalExercises) {
                    isResting = true
                } else {
                    if (currentPosition < totalExercises) {
                        currentPosition++
                        readyState = true
                        coroutineScope.launch {
                            delay(3000L)
                            readyState = false
                        }
                    } else {
                        onExerciseCompleted()
                    }
                }
            }
        }

        Column() {
            ExerciseProgressBar(totalExercises, currentPosition)
            Spacer(modifier = Modifier.height(30.dp))
            CurrentExercise(
                currentExercise,
                workoutDuration,
                totalExercises,
                currentPosition,
                readyState,
                isResting,
                goToWorkout = goToWorkout,
                goToExerciseList = goToExerciseList,
                goToInstructions = goToInstructions,
                goToNext = {
                    if (currentPosition < totalExercises) {
                        currentPosition++
                        readyState = true
                        coroutineScope.launch {
                            delay(3000L)
                            readyState = false
                        }
                    } else {
                        onExerciseCompleted()
                    }
                }
            )
        }
    }
}





