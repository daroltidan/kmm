import src.main.java.Deps
import src.main.java.Deps.Data.SQL
import src.main.java.Deps.Data.ktor

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.Koin) {
                    api(core)
                }

                with(Deps.Data) {
                    with(ktor) {
                        implementation(core)
                        implementation(logging)
                        implementation(contentNegotiation)
                        implementation(serialization)
                    }
                    with(SQL) {
                        implementation(common)
                        implementation(coroutinesExt)
                    }
                }
                implementation(Deps.Data.coroutines)
                implementation(Deps.Data.serialization)
                implementation(Deps.Core.logger)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                with(ktor) {
                    implementation(android)
                }
                with(Deps.Koin) {
                    implementation(android)
                }
                implementation(Deps.UI.viewmodel)
                with(SQL) {
                    implementation(android)
                }
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                with(ktor) {
                    implementation(ios)
                }
                with(SQL) {
                    implementation(ios)
                }
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 32
    }
}

kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
    binaries.all {
        binaryOptions["memoryModel"] = "experimental"
    }
}

sqldelight {
    database("KmmDatabase") {
        packageName = "com.daro.kmmtest.db"
    }
}
