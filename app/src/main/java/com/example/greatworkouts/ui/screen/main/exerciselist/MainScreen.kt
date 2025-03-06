package com.example.greatworkouts.ui.screen.main.exerciselist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.data.Category
import com.example.greatworkouts.data.Tool
import com.example.greatworkouts.ui.components.exerciselist.CategoryCard


@Composable
fun Main(
    categoryList: List<Category>,
    fitnessTools: List<Tool>,
    borderColor: Color,
    padding: PaddingValues,
    goToCategory: (String) -> Unit,
    goToTool: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(categoryList) { index, item ->
            val iconName = nameToDrawable(item.name)
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
            CategoryCard(
                item.name,
                shape,
                iconName,
                borderModifier,
                goToCategory = goToCategory
            )
        }

        item {
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Fitness Tools",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(20.dp))
        }

        itemsIndexed(fitnessTools) { index, item ->
            val iconName = nameToDrawable(item.name)
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                fitnessTools.size - 1 -> RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )

                else -> RoundedCornerShape(0.dp)
            }
            val borderModifier = when (index) {
                fitnessTools.size - 1 -> Modifier.border(Dp.Hairline, borderColor)
                else -> Modifier.border(BorderStroke(Dp.Hairline, borderColor))
            }
            CategoryCard(
                item.name,
                shape,
                iconName,
                borderModifier,
                goToCategory = goToTool
            )
        }
    }
}