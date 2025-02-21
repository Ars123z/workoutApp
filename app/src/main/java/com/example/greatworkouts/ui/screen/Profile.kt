package com.example.greatworkouts.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.data.settingData
import com.example.greatworkouts.ui.components.IconWithText
import com.example.greatworkouts.utils.getImageBitmapFromAssets

@Composable
fun Profile(
    navigate: () -> Unit
) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier.padding(start = 16.dp, top = 32.dp)) {
        item() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Image(
                    bitmap = getImageBitmapFromAssets(context, "plans/fit_&_strong.jpg")!!,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape)

                )
                Text(text = "Arsalan Naziri", style = MaterialTheme.typography.headlineMedium)
            }
            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 32.dp)
            )
        }
        items(settingData) { settings ->
            IconWithText(
                icon = settings.icon,
                text = settings.name,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        item() {
            Button(
                onClick = navigate,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 48.dp)
            ) {
                Text(text = "Logout")
            }
        }
    }
}
