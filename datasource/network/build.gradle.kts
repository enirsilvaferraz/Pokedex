plugins {
    alias(libs.plugins.pokedex.kmp.library)
    alias(libs.plugins.pokedex.koin.annotations)
    alias(libs.plugins.foundation.ktor)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(path = ":business:entity"))
                implementation(project(path = ":adapters:repositories"))
            }
        }
    }
}