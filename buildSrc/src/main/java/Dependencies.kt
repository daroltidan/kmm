package src.main.java

object Versions {
    const val koin = "3.2.0"
    const val ktorVersion = "2.1.0"
    const val coroutines = "1.6.4"
    const val lifecycle = "2.4.1"
    const val sqlDelight = "1.5.3"
}

object Deps {

    object Core {
        const val logger = "io.github.aakira:napier:2.6.1"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Data {

        object SQL {
            const val common = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
            const val android = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
            const val ios = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
            const val coroutinesExt = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"

        }

        object ktor {
            const val core = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
            const val logging = "io.ktor:ktor-client-logging:${Versions.ktorVersion}"
            const val contentNegotiation =
                "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
            const val serialization =
                "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"
            const val android = "io.ktor:ktor-client-android:${Versions.ktorVersion}"
            const val ios = "io.ktor:ktor-client-darwin:${Versions.ktorVersion}"
        }

        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.3"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    }

    object UI {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }


}
