package com.example.greatworkouts.ui.screen.profile.privacycenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.ui.components.GenericTopBar

@Composable
fun Main(
    goToTermsOfService: () -> Unit,
    goToPrivacyPolicy: () -> Unit,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            GenericTopBar(
            heading = "Privacy Center",
            goBack = { goBack() }
        )
            },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .padding(top=16.dp)
        ) {
            Text(
                text = "Terms of Service",
                modifier = Modifier
                    .padding(horizontal= 16.dp)
                    .clickable {
                        goToTermsOfService()
                    }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "Privacy Policy",
                modifier = Modifier
                    .padding(horizontal= 16.dp)
                    .clickable {
                        goToPrivacyPolicy()
                    }
            )
        }
    }
}