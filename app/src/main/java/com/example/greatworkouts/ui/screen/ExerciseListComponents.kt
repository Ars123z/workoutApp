package com.example.greatworkouts.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySection(heading: String, items: List<String>, selectedItem:List<String>, addItems: (String)-> Unit) {

    Column {
        Text(
            text = heading,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp)
        )
        FlowRow(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            maxItemsInEachRow = 3,
        ) {
            items.forEach { category ->
                var isSelected = selectedItem.contains(category)
                Button(
                    onClick = {
                        addItems(category)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color.Black else Color.White,
                        contentColor = if (isSelected) Color.White else Color.Black
                    ),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
