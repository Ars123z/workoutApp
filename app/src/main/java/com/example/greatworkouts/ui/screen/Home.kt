package com.example.greatworkouts.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.greatworkouts.FullScreenVideoPlayer
import com.example.greatworkouts.utils.getVideoUriFromAssets

@Composable
fun Plans(
    navigate: () -> Unit
) {
    val context = LocalContext.current
    val videoUri = getVideoUriFromAssets(context, "videos", "calf_raises.mp4")
    Box() {
        FullScreenVideoPlayer(videoUri = videoUri)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome To Home!!",
                color = MaterialTheme.colorScheme.onBackground
            )
            Button(
                onClick = { navigate() }
            ) {
                Text(
                    text = "Navigate"
                )
            }
        }
    }
}