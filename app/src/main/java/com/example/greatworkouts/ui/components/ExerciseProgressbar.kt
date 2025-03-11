package com.example.greatworkouts.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseProgressBar(totalExercises: Int, currentPosition: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 1 until totalExercises + 1) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .background(
                        color = when {
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