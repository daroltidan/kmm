import src.main.java.Deps

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.daro.kmmtest.android"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    with(Deps.Koin) {
        implementation(core)
        implementation(android)
    }
}
