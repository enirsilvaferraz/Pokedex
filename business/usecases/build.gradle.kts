plugins {
    alias(libs.plugins.pokedex.kmp.project)
    alias(libs.plugins.pokedex.kmp.library)
    alias(libs.plugins.pokedex.koin.annotations)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(path = ":business:entity"))
                implementation(project(path = ":adapters::repositories")) // TODO Verificar dependencia?
                implementation(libs.paging.common)
            }
        }
    }
}