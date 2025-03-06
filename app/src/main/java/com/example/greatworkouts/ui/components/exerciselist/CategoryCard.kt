package com.example.greatworkouts.ui.components.exerciselist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CategoryCard(
    name: String,
    shape: Shape,
    iconName: Int,
    borderModifier: Modifier,
    goToCategory: (String) -> Unit
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
            .clickable { goToCategory(name) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 16.dp)
        ) {
            Icon(
                painter = painterResource(iconName),
                contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier.size(70.dp)
            )
            Spacer(Modifier.weight(0.1f))
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Default.ChevronRight,
                tint = Color.DarkGray,
                contentDescription = null,
            )
        }
    }
}