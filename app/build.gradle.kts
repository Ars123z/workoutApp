plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.spotless)
}

android {
    namespace = "com.example.greatworkouts"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.greatworkouts"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to  "$projectDir/schemas".toString())
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude(layout.buildDirectory.map { it.asFile.absolutePath + "/**/*.kt" })
        targetExclude("bin/**/*.kt")

        ktlint("0.41.0")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout.compose)
//    ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
//    Media3
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.common)
//    Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animation)
//    Icons
    implementation(libs.androidx.material.icons.extended)
//    Room
    implementation(libs.androidx.room.runtime) // Use latest version
    ksp(libs.androidx.room.compiler) // For annotation processing (Kotlin)
    implementation(libs.androidx.room.ktx)
    // Add a dependency of Health Connect SDK
    implementation(libs.androidx.connect.client)
    // To bridge between ListenableFuture and suspend functions
    implementation(libs.androidx.concurrent.futures.ktx)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
//    Worker
    implementation(libs.androidx.work.runtime.ktx)
//    Graphing Library
    implementation(libs.composable.graphs)
// Pager
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
}