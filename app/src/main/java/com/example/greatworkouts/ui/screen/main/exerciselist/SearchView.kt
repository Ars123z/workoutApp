package com.example.greatworkouts.ui.screen.main.exerciselist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.ui.components.exerciselist.ExerciseCard
import com.example.greatworkouts.ui.screen.main.workoutmain.categoryList

@Composable
fun Search(
    exerciseList: List<Exercise>,
    padding: PaddingValues,
    borderColor: Color,
    goToInstructions: (String) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(exerciseList) { index, item ->
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                categoryList.size - 1 -> RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )

                else -> RoundedCornerShape(0.dp)
            }
            val borderModifier = when (index) {
                categoryList.size - 1 -> Modifier.border(Dp.Hairline, borderColor)
                else -> Modifier.border(BorderStroke(Dp.Hairline, borderColor))
            }
            ExerciseCard(
                name = item.name,
                shape = shape,
                thumbnail = item.thumbnail,
                context = context,
                borderModifier = borderModifier,
                goToInstructions =  goToInstructions
            )
        }
    }
}