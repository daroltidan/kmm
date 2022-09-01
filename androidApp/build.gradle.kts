import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
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
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    with(Deps.UI) {
        implementation(viewmodel)
        implementation(material3)
        implementation(composeUI)
        implementation(activityCompose)
        implementation(savedState)
        implementation(coilCompose)
        implementation(composeTooling)
        implementation(icons)
    }
    with(Deps.Koin) {
        implementation(core)
        implementation(android)
    }

    with(Deps.Debug) {
        debugImplementation(composeTooling)
        debugImplementation(composeManifest)
    }
}
