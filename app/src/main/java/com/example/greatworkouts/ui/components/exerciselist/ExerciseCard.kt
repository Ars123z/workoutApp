package com.example.greatworkouts.ui.components.exerciselist

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.utils.getImageBitmapFromAssets

@Composable
fun ExerciseCard(
    name: String,
    shape: Shape,
    thumbnail: String,
    context: Context,
    goToInstructions: (String) -> Unit,
    borderModifier: Modifier
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .then(borderModifier)
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                goToInstructions(name)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 16.dp)
        ) {
            Image(
                bitmap= getImageBitmapFromAssets(
                    context = context,
                    imagePath= thumbnail
                )!!,
                contentScale = ContentScale.Crop,
                contentDescription = "Exercise Thumbnail",
                modifier = Modifier.padding(end= 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .size(
                        width= 70.dp,
                        height= 50.dp
                    )
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}