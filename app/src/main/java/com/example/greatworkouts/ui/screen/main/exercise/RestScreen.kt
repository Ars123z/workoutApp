package com.example.greatworkouts.ui.screen.main.exercise

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun RestScreen(
    restingPeriod: Int,
) {
    var restingPeriodLeft by remember { mutableIntStateOf(restingPeriod) }
    val percent by remember(restingPeriodLeft) {
        derivedStateOf { restingPeriodLeft.toFloat() / restingPeriod.toFloat() }
    }

    LaunchedEffect(Unit) {
        while (restingPeriodLeft > 0) {
            delay(1000)
            restingPeriodLeft--
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(
                color = if (percent == 1f) Color.White else Color.Gray,
                radius = size.minDimension / 2,
                style = Stroke(width = 10.dp.toPx())
            )
            if (percent != 1f) {
                drawArc(
                    color = Color.White,
                    startAngle = -90f,
                    sweepAngle = 360 * percent,
                    useCenter = false,
                    style = Stroke(width = 10.dp.toPx())
                )
            }
        }

        Text(
            text = restingPeriodLeft.toString(),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}