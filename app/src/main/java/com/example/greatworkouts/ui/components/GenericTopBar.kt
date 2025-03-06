package com.example.greatworkouts.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GenericTopBar(
    heading: String,
    goBack: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = goBack
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Go Back",
                tint = Color.Black
            )
        }
        Text(
            text = heading,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}