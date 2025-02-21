package com.example.greatworkouts.utils

import android.content.res.Configuration
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun FullScreenVideoPlayer(videoUri: Uri?, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecyleOwner = LocalLifecycleOwner.current
    val orientation = context.resources.configuration.orientation

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ALL
        }
    }
    LaunchedEffect(videoUri) {
        if (videoUri != null) {
            val mediaItem = MediaItem.fromUri(videoUri)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        } else {
            exoPlayer.clearMediaItems()
        }
    }

    // Ensure the state for the lifecycle observer is updated correctly
    val currentOnPause by rememberUpdatedState(newValue = {
        exoPlayer.pause()
    })
    val currentOnResume by rememberUpdatedState(newValue = {
        exoPlayer.play()
    })
    val currentOnDestroy by rememberUpdatedState(newValue = {
        exoPlayer.release()
    })
    val currentOnStop by rememberUpdatedState(newValue = {
        exoPlayer.stop()
    })


    DisposableEffect(lifecyleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> currentOnPause
                Lifecycle.Event.ON_RESUME -> currentOnResume

                Lifecycle.Event.ON_DESTROY -> currentOnDestroy
                Lifecycle.Event.ON_STOP -> currentOnStop
                else -> {}
            }
        }
        lifecyleOwner.lifecycle.addObserver(observer)
        onDispose {
            exoPlayer.release() // Release the player when the composable is disposed
        }
    }
    Box(
        modifier = modifier
            .height(223.dp)
    ) {
        AndroidView(
            factory = { context ->
                val player = PlayerView(context)
                player.player = exoPlayer
                player.resizeMode =
                    if (orientation == Configuration.ORIENTATION_LANDSCAPE) AspectRatioFrameLayout.RESIZE_MODE_FILL else AspectRatioFrameLayout.RESIZE_MODE_FIT
                player.controllerHideOnTouch = true
                player.useController = false
                player
            },

            )
    }
}