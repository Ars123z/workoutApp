package com.example.greatworkouts.ui.components.healthconnect

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.greatworkouts.R

@Composable
fun InstalledMessage() {
    Text(
        text = stringResource(id= R.string.installed_welcome_message),
        textAlign = TextAlign.Justify
    )
}