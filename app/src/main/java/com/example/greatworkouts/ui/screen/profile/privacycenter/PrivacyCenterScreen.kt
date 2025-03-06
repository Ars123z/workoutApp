package com.example.greatworkouts.ui.screen.profile.privacycenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.ui.components.GenericTopBar

@Composable
fun PrivacyCenterScreen(
    goToTermsOfService: () -> Unit,
    goToPrivacyPolicy: () -> Unit,
    goBack: () -> Unit
) {
    var ActiveScreen by  remember {mutableStateOf("Main")}
    when (ActiveScreen) {
        "Main" -> Main(
            goToTermsOfService = { ActiveScreen = "Terms of Service" },
            goToPrivacyPolicy = { ActiveScreen = "Privacy Policy" },
            goBack = { goBack() }
        )
        "Terms of Service" -> TermsOfService(
            goBack = { ActiveScreen = "Main" }
        )
        "Privacy Policy" -> PrivacyPolicy(
            goBack = { ActiveScreen = "Main" }
        )
    }
}