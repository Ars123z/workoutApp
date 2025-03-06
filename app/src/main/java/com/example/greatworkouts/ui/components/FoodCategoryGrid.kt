package com.example.greatworkouts.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BreakfastDining
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GridItem() {
    Box(
        modifier = Modifier
            .aspectRatio(16f/9f) // Make items square
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Icon(
                imageVector = Icons.Filled.BreakfastDining,
                contentDescription = "Breakfast",
                modifier = Modifier.padding(bottom= 8.dp)
                    .align(Alignment.CenterHorizontally),
                tint= Color.DarkGray
            )
            Text(
                text = "Breakfast",
                textAlign = TextAlign.Center
            )
        }
    }
}