package com.example.greatworkouts.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun getUriFromAssets(context: Context, subDir: String, fileName: String): Uri? {
    val file = File(context.cacheDir, fileName)
    if (!file.exists()) {
        try {
            context.assets.open("$subDir/$fileName").use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
    return Uri.fromFile(file)
}

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

fun getVideoUriFromAssets(context: Context, dirName: String, fileName: String): Uri? {
    val file = File(context.cacheDir, fileName)
    if (!file.exists()) {
        try {
            context.assets.open("$dirName/$fileName").use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
    return Uri.fromFile(file)
}