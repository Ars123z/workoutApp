package com.example.greatworkouts.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.data.Workout

@Composable
fun WorkoutCategory(
    title: String,
    description: String,
    imagePath: String,
    workoutCategoryViewModel: WorkoutCategoryViewModel = viewModel(factory = WorkoutCategoryViewModel.Factory),
    goToWorkout: (String) -> Unit

) {
    val workoutList = workoutCategoryViewModel.allWorkouts.collectAsState(initial = emptyList())
    val context = LocalContext.current
    val imageBitmap = getImageBitmapFromAssets(context, imagePath)
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Adjusted height for at least 1/4 of the phone's height
        ) {
            Image(
                bitmap = imageBitmap!!,
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
                            colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background)
                        )
                    )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .offset(y = 100.dp) // Shift the text upwards

                )
            }
        }
        Text(
            text = "In the quiet embrace of twilight, across the forgotten garden, where ancient roses intertwined",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (workoutList.value.isNotEmpty()) {

            Column() {
                for (workout in workoutList.value.filter { it.subCategory == null }) {
                WorkoutCard(workout = workout,
                    goToWorkout = goToWorkout)
            }
            }
            Text(
                text= "Abs & Core",
                style= MaterialTheme.typography.headlineSmall,
            )
            Column() {
                for (workout in workoutList.value.filter { it.subCategory == "Abs & Core" }) {
                WorkoutCard(workout = workout, goToWorkout = goToWorkout)
            }
            }

        }
    }


}

@Composable
fun WorkoutCard(
    workout: Workout,
    goToWorkout: (String) -> Unit = {}
) {
    val context = LocalContext.current
    Log.d("Image path:", workout.coverImage)
    val imageBitmap = getImageBitmapFromAssets(context, workout.coverImage)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 8.dp)
            .clickable { goToWorkout(workout.name) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = imageBitmap!!,
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
            Text(
                text = workout.name,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}
