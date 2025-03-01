package com.example.greatworkouts.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greatworkouts.utils.getImageBitmapFromAssets

@Composable
fun FoodCard(
    name: String,
    cookingTime: String,
    calories: String,
    coverImage: String,
) {
    Card(
        modifier = Modifier.width(250.dp)
            .height(270.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        val context = LocalContext.current
        val imageBitmap =
            getImageBitmapFromAssets(context, coverImage)

        Column() {
            Box(
                modifier = Modifier.width(250.dp)
                    .height(150.dp)
            ) {
                Image(
                    bitmap = imageBitmap!!,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(50.dp) // Height of the gradient overlay
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White
                                )
                            )
                        )
                )
            }
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Black, fontSize = 18.sp,
                    lineHeight = 20.sp
                ),
                fontSize = 20.sp,
                modifier = Modifier
                    .offset(y = (-12).dp)
                    .padding(horizontal = 16.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Row(
                    verticalAlignment =Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                    Text(text = calories)
                }
                Spacer(
                    modifier = Modifier.height(
                        12.dp
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                    Text(text = cookingTime)
                }
            }
        }
    }
}