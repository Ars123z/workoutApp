package com.example.greatworkouts.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.FullScreenVideoPlayer
import com.example.greatworkouts.utils.getImageBitmapFromAssets
import com.example.greatworkouts.utils.getVideoUriFromAssets
import kotlin.io.path.Path
import kotlin.io.path.name


@Composable
fun Instruction(
    exerciseName: String,
    modifier: Modifier = Modifier,
    exerciseViewModel: ExerciseViewModel = viewModel(factory = ExerciseViewModel.Factory)
) {
    val exercise =
        exerciseViewModel.getExerciseByName(exerciseName).collectAsState(initial = null).value

    if (exercise != null) {
        val filePath = Path(exercise.videoPath)
        Log.d("ExerciseViewModel", "Video Path: $filePath")

        val videoPath = filePath.name
        val context = LocalContext.current
        val imageBitmap = getImageBitmapFromAssets(context, "muscle_focus/sample.jpg")
        val videoUri = getVideoUriFromAssets(context, "videos", "$videoPath")
        Log.d("ExerciseViewModel", "Video URI: $videoUri")
        val categories = exerciseViewModel.getExerciseCategories(exercise.name)
            .collectAsState(initial = emptyList())
        Scaffold(
            modifier = modifier,
            topBar = {

            }
        ) { contentPadding ->
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = exercise.name,
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Category: ",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal
                        )
                        categories.value.forEach { it ->
                            Text(
                                text = "${it.name}, ",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Gray,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    FullScreenVideoPlayer(
                        videoUri = videoUri,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    // Ensure video player is properly placed
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = "Hints",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = exercise.hints,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = "Tips",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = exercise.breathing,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = "Muscle Focus",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Image(
                        bitmap = imageBitmap!!,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}