package com.example.greatworkouts.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.ui.screen.nameToDrawable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessToolsBottomSheet(fitnessTool: List<String>, onDismiss: () -> Unit, onDone: (List<String>) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            sheetState.show()
        }
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Column() {
            BottomSheetContent(fitnessTool, onDismiss, onDone)

        }
    }
}

@Composable
fun BottomSheetContent(
    fitnessTool: List<String>,
    onDismiss: () -> Unit,
    onDone: (List<String>) -> Unit
) {
    var tools = listOf<String>(
        "BOSU",
        "Dumbbell",
        "Foam Roller",
        "Kettlebell",
        "Medicine Ball",
        "Pull Up Bar",
        "Suspension System",
        "Swiss Ball",
        "Barbell",
        "Resistance Band"
    )
    var selectedTools: MutableList<String> = remember { mutableStateListOf<String>() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onDismiss
        ) {
            Icon(imageVector = Icons.Default.Cancel, contentDescription = "Back")
        }
        Text(
            text= "Fitness Tools",
            style= MaterialTheme.typography.headlineMedium,
            modifier= Modifier.padding(start = 48.dp)
        )
    }
    LazyColumn() {
        items(tools) { tool ->
            val icon = nameToDrawable(tool)
            ToolSwitch(fitnessTool, tool, icon) { toolName -> if (selectedTools.contains(toolName)) selectedTools.remove(toolName) else selectedTools.add(toolName) }
        }
    }
    Button(
        onClick = {
            onDismiss()
            onDone(selectedTools)
        }
    ) {
        Text(text = "Done")
    }
}