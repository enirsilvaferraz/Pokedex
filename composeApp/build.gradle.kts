plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.pokedex.cmp.library)
    alias(libs.plugins.pokedex.kmp.application)
    alias(libs.plugins.pokedex.koin.annotations)
}

kotlin {
    sourceSets {
        commonMain.dependencies {

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)

            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.paging.common)
            implementation(libs.paging.compose.common)

            implementation(project(path = ":business:entity"))
            implementation(project(path = ":business:usecases"))
            implementation(project(path = ":adapters::repositories"))
            implementation(project(path = ":datasource:network"))
            implementation(project(path = ":datasource:database"))
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}