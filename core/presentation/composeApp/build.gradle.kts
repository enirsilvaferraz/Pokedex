import com.eferraz.pokedex.application

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.foundation.compose)
    alias(libs.plugins.foundation.kmp.application)
    alias(libs.plugins.foundation.koin)
}

application {
    namespace = "com.eferraz.pokedex"
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

            implementation(project(path = ":entity"))
            implementation(project(path = ":usecases"))
            implementation(project(path = ":repositories"))
            implementation(project(path = ":network"))
            implementation(project(path = ":database"))
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}