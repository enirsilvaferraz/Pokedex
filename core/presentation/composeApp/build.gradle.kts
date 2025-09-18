import com.eferraz.buildlogic.androidApplication
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.project.application)
    alias(libs.plugins.foundation.library.compose)
    alias(libs.plugins.foundation.library.koin)
    alias(libs.plugins.foundation.library.navigation)
}

androidApplication {
    namespace = "com.eferraz.pokedex"
    versionCode = 1
    versionName = "1.0"
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {

        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.runtimeCompose)

        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor3)

        implementation(libs.paging.common)
        implementation(libs.paging.compose.common)

        implementation(project(":entity"))
        implementation(project(":usecases"))
        implementation(project(":repositories"))
        implementation(project(":network"))
        implementation(project(":database"))
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}