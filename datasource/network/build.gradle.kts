plugins {
    alias(libs.plugins.pokedex.kmp.project)
    alias(libs.plugins.pokedex.kmp.library)
    alias(libs.plugins.pokedex.koin.annotations)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {

    sourceSets {

        commonMain {
            dependencies {

                implementation(project(path = ":business:entity"))
                implementation(project(path = ":adapters:repositories"))

                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.encoding)
                implementation(libs.kermit)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}