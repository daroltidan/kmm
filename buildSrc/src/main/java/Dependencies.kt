package src.main.java

object Versions {
    const val koin = "3.2.0"
    const val ktorVersion = "2.0.2"
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
        const val contentNegociation =
            "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktorVersion}"
        const val ios = "io.ktor:ktor-client-darwin:${Versions.ktorVersion}"
    }
}
