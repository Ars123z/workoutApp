package com.example.greatworkouts.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.greatworkouts.ui.screen.nameToDrawable
import kotlinx.coroutines.launch

@Composable
fun OptionCard(
    title: String,
    icon: ImageVector,
    showChevron: Boolean,
    value: Any? = null,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
            Text(
                text = title,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = value?.toString() ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            if (showChevron) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ToggleOptionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 8.dp)
            .clickable { onCheckedChange(!checked) },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = title, style = MaterialTheme.typography.bodyLarge)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = Color.Blue,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }
    }
}

@Composable
fun IconWithText(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null)
        Text(text = text)
    }
}


@Composable
fun DurationDialog(
    initialDuration: Int,
    onDismissRequest: () -> Unit,
    onDurationSelected: (Int) -> Unit
) {
    var selectedDuration by remember { mutableIntStateOf(initialDuration) }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            val listState = rememberLazyListState(
                initialFirstVisibleItemIndex = initialDuration - 1
            )
            LaunchedEffect(listState) {
                snapshotFlow { listState.firstVisibleItemIndex }.collect { index ->
                    selectedDuration = index + 2 // Adjust based on the middle item
                }
            }
            Box(
                modifier = Modifier
                    .height(115.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    state = listState,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(60) { minute ->
                        val isMiddle = listState.firstVisibleItemIndex == minute - 1
                        Text(
                            text = "${minute + 1}",
                            style = if (isMiddle) {
                                MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 40.sp
                                )
                            } else {
                                MaterialTheme.typography.bodyLarge
                            },
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            textAlign = TextAlign.Center
                        )
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(2.dp)
                                .background(Color.Gray)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = onDismissRequest) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    onDurationSelected(selectedDuration)
                    onDismissRequest()
                }
                ) {
                    Text("OK")
                }
            }
        }
    }
}

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

@Composable
fun ToolSwitch(
    fitnessTool: List<String>,
    toolName: String,
    icon: Int,
    onCheckedChange: (String) -> Unit
) {
    var checked by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        checked = fitnessTool.contains(toolName)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(horizontal= 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Gray,
                modifier= Modifier.size(80.dp)
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
