package com.example.greatworkouts.utils

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Locale

fun dateTimeWithOffsetOrDefault(time: Instant, offset: ZoneOffset?): ZonedDateTime =
    if (offset != null) {
        ZonedDateTime.ofInstant(time, offset)
    } else {
        ZonedDateTime.ofInstant(time, ZoneId.systemDefault())
    }

fun Duration.formatTime() = String.format(
    Locale.US,
    "%02d:%02d:%02d",
    this.toHours() % 24,
    this.toMinutes() % 60,
    this.seconds % 60
)

fun Duration.formatHoursMinutes() = String.format(
    Locale.US,
    "%01dh%02dm",
    this.toHours() % 24,
    this.toMinutes() % 60
)