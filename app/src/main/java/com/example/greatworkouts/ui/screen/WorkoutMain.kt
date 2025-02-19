package com.example.greatworkouts.ui.screen

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.IOException

val categoryList = listOf(
    listOf("Strength", "workout_main/torso_clean.png"),
    listOf("Flexibility", "workout_main/torso_clean.png"),
    listOf("Endurance", "workout_main/torso_clean.png"),
    listOf("Strength", "workout_main/torso_clean.png"),
    listOf("BreathWork", "workout_main/torso_clean.png")
)

@Composable
fun WorkoutMain(
    goToCategory: (String) -> Unit,
    goToMenu: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item() {
            WorkoutHeader(onMenuClick = { goToMenu() })
        }
        items(count = categoryList.size) { item ->
            CategoryCard(name = categoryList[item][0], path = categoryList[item][1], goToCategory = goToCategory)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun WorkoutHeader(
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Workouts",
            style = MaterialTheme.typography.headlineMedium
        )
        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu"
            )
        }
    }
}



@Composable
fun CategoryCard(
    name: String,
    path: String,
    goToCategory: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val imageBitmap = getImageBitmapFromAssets(context, path)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 8.dp)
            .clickable { goToCategory(name) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black
            )
            Image(
                bitmap = imageBitmap!!,
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )
        }
    }
}

fun getImageBitmapFromAssets(
    context: Context,
    imagePath: String
): ImageBitmap? {
    return try {
        val inputStream = context.assets.open(imagePath)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        bitmap.asImageBitmap()
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}