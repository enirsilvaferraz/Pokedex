plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.room)
    alias(libs.plugins.foundation.koin)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {

                implementation(project(path = ":business:entity"))
                implementation(project(path = ":adapters:repositories"))

                implementation(libs.paging.common)
            }
        }
    }
}