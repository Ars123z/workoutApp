package com.example.greatworkouts.ui.screen.profile.sleep

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.data.HealthConnectAvailability
import com.example.greatworkouts.R
import com.example.greatworkouts.ui.components.healthconnect.InstalledMessage
import com.example.greatworkouts.ui.components.healthconnect.NotInstalledMessage
import java.time.ZonedDateTime
import java.util.UUID

@Composable
fun SleepScreen(
    healthConnectAvailability: HealthConnectAvailability,
    sleepViewModel: SleepViewModel = viewModel(
        factory = SleepViewModel.factory
    ),
    onResumeAvailabilityCheck: () -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val permissionsGranted by sleepViewModel.permissionsGranted
    val sessionsList by sleepViewModel.sessionsList
    val permissions = sleepViewModel.permissions
    val onPermissionsResult = { sleepViewModel.initialLoad() }
    val permissionsLauncher =
        rememberLauncherForActivityResult(sleepViewModel.permissionsLauncher) {
            onPermissionsResult()
        }

    val currentOnAvailabilityCheck by rememberUpdatedState(onResumeAvailabilityCheck)

    // Add a listener to re-check whether Health Connect has been installed each time the Welcome
    // screen is resumed: This ensures that if the user has been redirected to the Play store and
    // followed the onboarding flow, then when the app is resumed, instead of showing the message
    // to ask the user to install Health Connect, the app recognises that Health Connect is now
    // available and shows the appropriate welcome.
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                currentOnAvailabilityCheck()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.5f),
            painter = painterResource(id = R.drawable.health_connect_logo),
            contentDescription = "Health Connect Logo"
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Welcome to Health Connect",
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(32.dp))
        when (healthConnectAvailability) {
            HealthConnectAvailability.INSTALLED -> Sleep(
                permissions = permissions,
                permissionsGranted = permissionsGranted,
                sessionsList = sessionsList,
                uiState = sleepViewModel.uiState,
                onPermissionsResult = { sleepViewModel.initialLoad() },
                onPermissionsLaunch = { values ->
                    permissionsLauncher.launch(values)
                },
                onInsertClick = { sleepViewModel.insertExerciseSession() },
            )
            HealthConnectAvailability.NOT_INSTALLED -> NotInstalledMessage()
        }
    }
}

@Composable
fun Sleep(
    permissions: Set<String>,
    permissionsGranted: Boolean,
    sessionsList: List<ExerciseSessionRecord>,
    uiState: SleepViewModel.UIState,
    onInsertClick: () -> Unit = {},
    onError: (Throwable?) -> Unit = {},
    onPermissionsResult: () -> Unit = {},
    onPermissionsLaunch: (Set<String>) -> Unit = {},
) {
    val errorId = rememberSaveable { mutableStateOf(UUID.randomUUID()) }

    LaunchedEffect(uiState) {
        // If the initial data load has not taken place, attempt to load the data.
        if (uiState is SleepViewModel.UIState.Uninitialized) {
            onPermissionsResult()
        }
        // The [ExerciseSessionViewModel.UiState] provides details of whether the last action was a
        // success or resulted in an error. Where an error occurred, for example in reading and
        // writing to Health Connect, the user is notified, and where the error is one that can be
        // recovered from, an attempt to do so is made.
        if (uiState is SleepViewModel.UIState.Error && errorId.value != uiState.uuid) {
            onError(uiState.exception)
            errorId.value = uiState.uuid
        }
    }

if (uiState != SleepViewModel.UIState.Uninitialized) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!permissionsGranted) {
            item {
                Button(
                    onClick = {
                        onPermissionsLaunch(permissions)
                    }
                ) {
                    Text(text = stringResource(R.string.permissions_button_label))
                }
            }
        } else {
            item {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(4.dp),
                    onClick = {
                        onInsertClick()
                    }
                ) {
                    Text(stringResource(id = R.string.insert_exercise_session))
                }
            }

            items(sessionsList) { session ->
                Text(
                    text= session.title ?: "No title"
                )
            }
        }
    }
}
}
