plugins {
    alias(libs.plugins.pokedex.kmp.library)
    alias(libs.plugins.pokedex.koin.annotations)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(path = ":business:entity"))
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.paging.common)
            }
        }
    }
}