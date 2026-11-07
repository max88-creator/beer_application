plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.example.lacknerpagination"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.lacknerpagination"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.room.paging)

    implementation(libs.moshi.kotlin)
    implementation (libs.converter.moshi)


    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.compose)
    ksp(libs.androidx.room.compiler)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.androidx.room.ktx)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.okhttp.logging)
    implementation(libs.coil.compose)
    implementation(libs.serialization)
    implementation(libs.kotlinx.coroutines.android)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

