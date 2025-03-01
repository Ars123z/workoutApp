package com.example.greatworkouts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@androidx.compose.runtime.Composable
fun ToolSwitch(
    fitnessTool: List<String>,
    toolName: String,
    icon: Int,
    onCheckedChange: (String) -> Unit
) {
    var checked by androidx.compose.runtime.remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        checked = fitnessTool.contains(toolName)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Companion.Gray,
                modifier = Modifier.size(80.dp)
            )
            Text(text = toolName)
        }
        Switch(
            checked = checked,
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color.Green,
                uncheckedTrackColor = Color.Gray,
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.White
            ),
            onCheckedChange = {
                checked = it
                onCheckedChange(toolName)
            },

            )
    }
}