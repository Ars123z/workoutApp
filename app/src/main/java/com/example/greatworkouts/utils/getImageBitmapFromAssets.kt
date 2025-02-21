package com.example.greatworkouts.utils

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.IOException

fun getImageBitmapFromAssets(
    context: Context,
    imagePath: String
): ImageBitmap? {
    return try {
        val inputStream = context.assets.open(imagePath)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        bitmap.asImageBitmap()
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}