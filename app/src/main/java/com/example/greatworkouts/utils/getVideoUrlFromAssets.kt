package com.example.greatworkouts.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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