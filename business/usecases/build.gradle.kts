plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.koin)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(path = ":business:entity"))
                //implementation(project(path = ":adapters::repositories")) // TODO Dependencia proibida
                implementation(libs.paging.common)
            }
        }
    }
}