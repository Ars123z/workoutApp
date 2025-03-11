package com.example.greatworkouts.ui.screen.main.exerciselist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


val list = listOf(
    "Abs & Core",
    "Upper Body",
    "Lower Body",
    "Cardio",
    "Stretching"
)
@Composable
fun ExerciseByToolScreen(
    toolName: String,
    goBack: () -> Unit,
    goToInstructions: (String) -> Unit,
    exerciseListViewModel: ExerciseListViewModel = viewModel(factory = ExerciseListViewModel.factory)
) {
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(60.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { goBack() }
                ) {
                    Icon(
                        Icons.Default.ChevronLeft,
                        tint = Color.Black,
                        contentDescription = null,
                    )
                }
                Text(
                    text =toolName,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier= Modifier.padding(contentPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(list) { item ->
                Card(
                    colors= CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier= Modifier.clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal= 16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(
                            text= item,
                            style= MaterialTheme.typography.headlineSmall
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Down"
                        )
                    }
                }
            }
        }
    }
}
