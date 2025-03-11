package com.example.greatworkouts.ui.screen.main.exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.utils.FullScreenVideoPlayer
import com.example.greatworkouts.utils.getVideoUriFromAssets
import kotlinx.coroutines.delay
import java.io.File

@Composable
fun CurrentExercise(
    exercise: Exercise,
    workoutDuration: Int,
    totalExercises: Int,
    currentPosition: Int,
    readyState: Boolean,
    isResting: Boolean,
    goToWorkout: () -> Unit,
    goToInstructions: (String) -> Unit,
    goToExerciseList: () -> Unit,
    goToNext: () -> Unit
) {
    val context = LocalContext.current
    val videoDir = "video"
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
        if (currentPosition % 5 == 1 && currentPosition != 1) {
            round++
        }
    }
    LaunchedEffect(exercise, readyState) {
        if (!readyState) {
            while (true) {
                delay(1000)
                seconds++
            }
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
                                text = currentPosition.toString(),
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
            Spacer(modifier = Modifier.height(140.dp))
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
                            onClick = { goToInstructions(exercise.name) },
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
            RestScreen(restingPeriod = 25)
        }
    }
}