package com.example.greatworkouts.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.data.Tool
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun Workouts1(
    name: String? = null,
    toExercise: () -> Unit = {},
    workoutViewModel: WorkoutViewModel = viewModel(factory = WorkoutViewModel.Factory)
) {

    val workout = workoutViewModel.getWorkoutsByName(name!!).collectAsState(initial = null)
    val exercises = workout.value?.let {
        workoutViewModel.getExercisesByWorkout(it.name).collectAsState(initial = emptyList()).value
    } ?: emptyList()

    var isWarmupChecked by remember { mutableStateOf(false) }
    var isQuietWorkoutChecked by remember { mutableStateOf(false) }
    var duration by remember { mutableIntStateOf(30) }
    var calories by remember { mutableIntStateOf(180) }
    val fitnessTool = remember { mutableStateListOf<Tool>() }
    val context = LocalContext.current
    val imageBitmap =
        if (workout.value != null) {
            getImageBitmapFromAssets(context, workout.value!!.coverImage)
        } else {
            null
        }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (button) = createRefs()

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom) // Align to the bottom of the screen
                }
                .zIndex(1f)
        ) {
            Text(
                text = "Start",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (workout.value != null && imageBitmap != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp) // Adjusted height for at least 1/4 of the phone's height
            ) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                // Gradient overlay to whiten the bottom part of the image
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(150.dp) // Height of the gradient overlay
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        )
                ) {
                    Text(
                        text = workout.value!!.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = 100.dp) // Shift the text upwards
                    )
                }
            }
            Text(
                text = workout.value!!.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = Color.Blue
                    )
                    Text(
                        text = "$duration minutes"
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                    Text(
                        text = "$calories calories burned"
                    )
                }
            }
            Column() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp)
                        .clickable { },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxHeight(), // Add horizontal padding
                        verticalAlignment = Alignment.CenterVertically, // Center vertically
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.MusicNote,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            text = "Music and Sound",
                            modifier = Modifier.padding(start = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp)
                        .clickable { },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxHeight(), // Add horizontal padding
                        verticalAlignment = Alignment.CenterVertically, // Center vertically
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            text = "Duration",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "$duration minutes"
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp)
                        .clickable { },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxHeight(), // Add horizontal padding
                        verticalAlignment = Alignment.CenterVertically, // Center vertically
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            text = "Fitness Tools",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        if (fitnessTool.isEmpty()) {
                            Text(
                                text = "None"
                            )
                        } else {
                            Text(
                                text = "${fitnessTool.size} items"
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp)
                        .clickable {
                            isWarmupChecked = !isWarmupChecked
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxHeight(), // Add horizontal padding
                        verticalAlignment = Alignment.CenterVertically, // Center vertically
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Column(
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = "Start with a warmup",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "3 minutes",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = isWarmupChecked,
                            onCheckedChange = { isWarmupChecked = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White,
                                checkedTrackColor = Color.Blue,
                                uncheckedTrackColor = Color.Gray
                            )
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp)
                        .clickable {
                            isQuietWorkoutChecked = !isQuietWorkoutChecked
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxHeight(), // Add horizontal padding
                        verticalAlignment = Alignment.CenterVertically, // Center vertically
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Column(
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = "Quiet Workout",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Avoid Jumping",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = isQuietWorkoutChecked,
                            onCheckedChange = { isQuietWorkoutChecked = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White,
                                checkedTrackColor = Color.Blue,
                                uncheckedTrackColor = Color.Gray
                            )
                        )
                    }
                }
                Text(
                    text = "Exercise List",
                    style= MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    textAlign = TextAlign.Start
                )
                if (exercises.isNotEmpty()) {
                    for (exercise in exercises) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(100f),
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            val thumbnail = exercise.thumbnail
                            val thumbnailBitmap =
                                getImageBitmapFromAssets(context, thumbnail)
                            Log.d("thumbnailBitmap", exercise.thumbnail)
                            Image(
                                bitmap = thumbnailBitmap!! , // Replace with your actual painter
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(50.dp)
                                    .width(80.dp)// Set the size here
                                    .clip(shape = RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column() {
                                Text(
                                    text = exercise.name,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "${exercise.duration} s",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(5.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}