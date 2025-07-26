plugins {
    alias(libs.plugins.pokedex.kmp.library)
    alias(libs.plugins.pokedex.room)
//    alias(libs.plugins.pokedex.koin.annotations) // Aguardando resolução do problema https://github.com/InsertKoinIO/koin/issues/2174
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {

                implementation(project(path = ":business:entity"))
                implementation(project(path = ":adapters:repositories"))

                // Koin
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)

                implementation(libs.paging.common)
            }
        }
    }
}