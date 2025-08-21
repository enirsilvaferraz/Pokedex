plugins {
    alias(libs.plugins.pokedex.kmp.library)
    alias(libs.plugins.pokedex.room)
    alias(libs.plugins.pokedex.koin.annotations)
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