package com.example.greatworkouts.ui.screen.profile.glucose

import android.os.RemoteException
import android.provider.Contacts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.health.connect.client.feature.ExperimentalFeatureAvailabilityApi
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.BloodGlucoseRecord
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.greatworkouts.GreatWorkoutsApplication
import com.example.greatworkouts.data.HealthConnectManager
import com.example.greatworkouts.ui.screen.profile.sleep.SleepViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.Duration
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlin.random.Random

class GlucoseViewModel(val healthConnectManager: HealthConnectManager): ViewModel() {
    val permissions = setOf(
        HealthPermission.getReadPermission(BloodGlucoseRecord::class),
        HealthPermission.getWritePermission(BloodGlucoseRecord::class)
    )

    var permissionsGranted = mutableStateOf(false)
    private set

    var sessionsList: MutableState<List<BloodGlucoseRecord>> = mutableStateOf(listOf())

    var uiState: UIState by mutableStateOf(UIState.Uninitialized)

    val permissionsLauncher = healthConnectManager.requestPermissionsActivityContract()

    fun initialLoad() {
        viewModelScope.launch {
            tryWithPermissionsCheck {
                readExerciseSessions()
            }
        }
    }

    fun insertBloodGlucoseRecord() {
        viewModelScope.launch {
            tryWithPermissionsCheck {
                val startOfDay = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS)
                val latestStartOfSession = ZonedDateTime.now().minusMinutes(30)
                val offset = Random.nextDouble()

                // Generate random start time between the start of the day and (now - 30mins).
                val startOfSession = startOfDay.plusSeconds(
                    (Duration.between(startOfDay, latestStartOfSession).seconds * offset).toLong()
                )

                healthConnectManager.writeBloodGlucoseRecord(startOfSession)
                readBloodGlucose()
            }
        }
    }

    private suspend fun readBloodGlucose() {
        val start = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(90)
        val now = Instant.now()

        sessionsList.value = healthConnectManager.readBloodGlucoseRecord(start.toInstant(), now)
    }

    @OptIn(ExperimentalFeatureAvailabilityApi::class)
    private suspend fun tryWithPermissionsCheck(block: suspend () -> Unit) {
        permissionsGranted.value = healthConnectManager.hasAllPermissions(permissions)
        uiState = try {
            if (permissionsGranted.value) {
                block()
            }
            UIState.Done
        } catch (remoteException: RemoteException) {
            UIState.Error(remoteException)
        } catch (securityException: SecurityException) {
            UIState.Error(securityException)
        } catch (ioException: IOException) {
            UIState.Error(ioException)
        } catch (illegalStateException: IllegalStateException) {
            UIState.Error(illegalStateException)
        }
    }

    private suspend fun readExerciseSessions() {
        val start = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(90)
        val now = Instant.now()

        sessionsList.value = healthConnectManager.readBloodGlucoseRecord(start.toInstant(), now)
    }

    sealed class UIState {
        object Uninitialized : UIState()
        object Done : UIState()

        // A random UUID is used in each Error object to allow errors to be uniquely identified,
        // and recomposition won't result in multiple snackbars.
        data class Error(val exception: Throwable, val uuid: UUID = UUID.randomUUID()) : UIState()
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GreatWorkoutsApplication)
                val healthConnectManager = application.healthConnectManager
                GlucoseViewModel(healthConnectManager)
            }
        }
    }
}