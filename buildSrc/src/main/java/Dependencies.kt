package src.main.java

object Versions {
    const val koin = "3.2.0"
    const val ktorVersion = "2.1.0"
    const val coroutines = "1.6.1"
    const val lifecycle = "2.4.1"
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
        object ktor {
            const val core = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
            const val logging = "io.ktor:ktor-client-logging:${Versions.ktorVersion}"
            const val contentNegociation =
                "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
            const val serialization =
                "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"
            const val okhttp = "io.ktor:ktor-client-okhttp:${Versions.ktorVersion}"
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