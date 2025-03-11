package com.example.greatworkouts.ui.screen.main.workout

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.ui.components.DurationDialog
import com.example.greatworkouts.ui.components.FitnessToolsBottomSheet
import com.example.greatworkouts.ui.components.IconWithText
import com.example.greatworkouts.ui.components.OptionCard
import com.example.greatworkouts.ui.components.ToggleOptionCard
import com.example.greatworkouts.utils.getImageBitmapFromAssets

@Composable
fun Workouts(
    name: String? = null,
    workoutViewModel: WorkoutViewModel = viewModel(factory = WorkoutViewModel.Companion.Factory),
    goToExerciseScreen: (String) -> Unit,
) {
    val workout = workoutViewModel.getWorkoutsByName(name!!).collectAsState(initial = null)
    val exercises = workout.value?.let {
        workoutViewModel.getExercisesByWorkout(it.name)
            .collectAsState(initial = emptyList()).value
    } ?: emptyList()

    var isWarmupChecked by remember { mutableStateOf(false) }
    var isQuietWorkoutChecked by remember { mutableStateOf(false) }
    var duration by remember { mutableIntStateOf(30) }
    var calories by remember { mutableIntStateOf(180) }
    var fitnessTool = remember { mutableStateListOf<String>() }
    val context = LocalContext.current
    val imageBitmap = workout.value?.let { getImageBitmapFromAssets(context, it.coverImage) }
    var showDurationDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 0.dp, horizontal = 16.dp)
    ) {
        val (image, description, infoRow, optionsColumn, button) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
                .height(150.dp) // Adjusted height for at least 1/4 of the phone's height
        ) {
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
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
                    text = workout.value?.name ?: "",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = 100.dp) // Shift the text upwards
                )
            }

        }

        workout.value?.let {

            Text(
                text = it.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(image.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }

        Row(
            modifier = Modifier.constrainAs(infoRow) {
                top.linkTo(description.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconWithText(Icons.Default.Timer, "$duration minutes")
            IconWithText(Icons.Default.LocalFireDepartment, "$calories calories burned")
        }
        Box(
            modifier = Modifier
                .constrainAs(optionsColumn) {
                    top.linkTo(infoRow.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxHeight(0.7f)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                OptionCard("Music and Sound", Icons.Default.MusicNote, true) {}
                OptionCard("Duration", Icons.Default.Timer, false, "$duration minutes") {showDurationDialog = true}
                if (fitnessTool.size == 1) {
                    OptionCard(
                        "Fitness Tools",
                        Icons.Default.FitnessCenter,
                        false,
                        "${fitnessTool[0]}"
                    ) { showBottomSheet = true }
                } else if (fitnessTool.size > 1) {
                    OptionCard(
                        "Fitness Tools",
                        Icons.Default.FitnessCenter,
                        false,
                        "${fitnessTool.size} items"
                    )
                }
                else {
                    OptionCard(
                        "Fitness Tools",
                        Icons.Default.FitnessCenter,
                        false,
                        "None"
                    ) { showBottomSheet = true }
                }
                ToggleOptionCard(
                    "Start with a warmup",
                    "3 minutes",
                    Icons.Default.Person,
                    isWarmupChecked
                ) { isWarmupChecked = it }
                ToggleOptionCard(
                    "Quiet Workout",
                    "Avoid Jumping",
                    Icons.Default.Person,
                    isQuietWorkoutChecked
                ) { isQuietWorkoutChecked = it }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Exercise List",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(modifier = Modifier.height(20.dp))

                if (exercises.isNotEmpty()) {
                    Column() {
                        for (exercise in exercises) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .zIndex(100f),
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                val thumbnail = "thumbnail/calf_raises.png"
                                val thumbnailBitmap =
                                    getImageBitmapFromAssets(context, thumbnail)
                                Log.d("thumbnailBitmap", exercise.thumbnail)
                                Image(
                                    bitmap = thumbnailBitmap!!, // Replace with your actual painter
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .height(50.dp)
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
        // Show the DurationDialog conditionally
        if (showDurationDialog) {
            DurationDialog(
                initialDuration = duration,
                onDismissRequest = { showDurationDialog = false },
                onDurationSelected = { newDuration ->
                    duration = newDuration
                }
            )
        }
        if (showBottomSheet) {
            FitnessToolsBottomSheet(
                fitnessTool,
                onDismiss = { showBottomSheet = false },
                onDone = {it -> fitnessTool = it as SnapshotStateList<String> }
            )
        }
        Button(
            onClick = {
                goToExerciseScreen(name)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text(text = "Start", style = MaterialTheme.typography.headlineSmall)
        }
    }
}







