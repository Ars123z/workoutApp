package com.example.greatworkouts.utils

import kotlinx.coroutines.CoroutineScope
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun showExceptionSnackbar(
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    throwable: Throwable?
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            message = throwable?.localizedMessage ?: "Unknown exception",
            duration = SnackbarDuration.Short
        )
    }
}

fun formatDisplayTimeStartEnd(
    startTime: Instant,
    startZoneOffset: ZoneOffset?,
    endTime: Instant,
    endZoneOffset: ZoneOffset?,
): String {
    val timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
    val start = timeFormatter.format(dateTimeWithOffsetOrDefault(startTime, startZoneOffset))
    val end = timeFormatter.format(dateTimeWithOffsetOrDefault(endTime, endZoneOffset))
    return "$start - $end"
}