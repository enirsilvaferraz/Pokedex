plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.koin)
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