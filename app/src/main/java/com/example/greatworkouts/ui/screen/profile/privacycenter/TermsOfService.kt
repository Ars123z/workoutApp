package com.example.greatworkouts.ui.screen.profile.privacycenter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.data.termsOfServiceData
import com.example.greatworkouts.ui.components.GenericTopBar

@Composable
fun TermsOfService(
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            GenericTopBar(
                heading = "Terms of Service",
                goBack = { goBack() }
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            items(termsOfServiceData) { item ->
                Heading(
                    content = item.heading
                )
                Body(
                    content = item.body
                )
            }
        }
    }
}

@Composable
fun Heading(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun Body(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.bodyMedium
    )
}

