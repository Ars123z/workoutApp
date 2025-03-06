package com.example.greatworkouts.ui.screen.main.exercise

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.utils.FullScreenVideoPlayer
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.ui.screen.main.workout.WorkoutViewModel
import com.example.greatworkouts.utils.getVideoUriFromAssets
import kotlinx.coroutines.delay
import java.io.File

@Composable
fun ExerciseScreen(
    name: String? = null,
    workoutViewModel: WorkoutViewModel = viewModel(factory = WorkoutViewModel.Companion.Factory),
    onExerciseCompleted: () -> Unit,
    goToInstructions: (String) -> Unit,
    goToWorkout: () -> Unit
) {
    val workout = workoutViewModel.getWorkoutsByName(name!!).collectAsState(initial = null)

    val exercises = workout.value?.let {
        workoutViewModel.getExercisesByWorkout(it.name)
            .collectAsState(initial = emptyList()).value
    } ?: emptyList()

    val workoutDuration by remember(exercises) { mutableIntStateOf(exercises.sumOf { it.duration }) }
    val totalExercises = exercises.size
    var currentPosition by remember { mutableIntStateOf(0) }
    var isResting by remember { mutableStateOf(false) }


    if (exercises.isNotEmpty()) {
        val currentExercise by remember(currentPosition) {
            mutableStateOf(exercises[currentPosition])
        }


        LaunchedEffect(currentPosition) {
            if (isResting) {
                delay(25000L) // 25 seconds rest
                isResting = false
                if (currentPosition < totalExercises - 1) {
                    currentPosition++
                } else {
                    onExerciseCompleted()
                }
            } else {
                delay(currentExercise.duration * 1000L)
                if ((currentPosition + 1) % 5 == 0) {
                    isResting = true
                } else {
                    if (currentPosition < totalExercises - 1) {
                        currentPosition++
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
                isResting,
                goToWorkout = goToWorkout,
                goToExerciseList = {},
                goToInstructions = goToInstructions,
                goToNext = {
                    if (currentPosition < totalExercises - 1) {
                        currentPosition++
                    } else {
                        onExerciseCompleted()
                    }
                }
            )
        }
    }


}

@Composable
fun CurrentExercise(
    exercise: Exercise,
    workoutDuration: Int,
    totalExercises: Int,
    currentPosition: Int,
    isResting: Boolean,
    goToWorkout: () -> Unit,
    goToInstructions: (String) -> Unit,
    goToExerciseList: () -> Unit,
    goToNext: () -> Unit
) {
    val context = LocalContext.current
    val videoDir = "videos"
    val iconContainerColor = Color(0xFFCCCCCC)
    val totalRound = totalExercises / 5
    var round by remember { mutableIntStateOf(1) }
    var duration by remember {
        mutableIntStateOf(workoutDuration)
    }
    val durationInMinutes by remember(duration) {
        derivedStateOf { duration / 60 }
    }
    val remainingSeconds by remember(duration) {
        derivedStateOf { duration % 60 }
    }
    val videoUri by remember(exercise) {
        val file = File(exercise.videoPath)
        mutableStateOf(getVideoUriFromAssets(context, videoDir, file.name))
    }
    var seconds by remember(exercise) { mutableIntStateOf(0) }

    LaunchedEffect(currentPosition) {
        if (currentPosition != 0 && currentPosition % 5 == 0) {
            round++
        }
    }
    LaunchedEffect(exercise) {
        while (true) {
            delay(1000)
            seconds++
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            duration--
        }
    }
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { goToWorkout() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = iconContainerColor
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = null
                )
            }
            Text(
                text = "$durationInMinutes : $remainingSeconds",
                style = MaterialTheme.typography.headlineMedium
            )
            IconButton(
                onClick = { goToExerciseList() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = iconContainerColor
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        }
        if (!isResting) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Round ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = "$round ",
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = "of ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = "$totalRound",
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Exercise ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = "${currentPosition + 1} ",
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = "of ",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = "$totalExercises",
                                style = MaterialTheme.typography.headlineLarge,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier= Modifier.height(140.dp))
            FullScreenVideoPlayer(
                videoUri = videoUri,
            )
            Column(
                modifier = Modifier.padding(top = 170.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "$seconds",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "s"
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = exercise.name,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        IconButton(
                            onClick = {goToInstructions(exercise.name)},
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = iconContainerColor
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null
                            )
                        }
                    }
                    IconButton(
                        onClick = { goToNext() },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = iconContainerColor
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null
                        )

                    }
                }
            }
        } else {
            Text(
                text= "I am resting sorry"
            )
        }
    }
}

@Composable
fun ExerciseProgressBar(totalExercises: Int, currentPosition: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 0 until totalExercises) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .background(
                        color= when  {
                            i < currentPosition -> Color.Black
                            i == currentPosition -> Color.Blue
                            else -> Color.Gray
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 2.dp)
            )
        }
    }
}
