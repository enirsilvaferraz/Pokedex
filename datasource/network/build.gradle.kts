plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.koin)
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