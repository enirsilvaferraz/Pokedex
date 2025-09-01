import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.foundation.compose)
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.koin)
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {

        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.runtimeCompose)

        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor3)

        implementation(libs.navigation.compose)
        implementation(libs.kotlinx.serialization.json)

        implementation(libs.paging.common)
        implementation(libs.paging.compose.common)

        implementation(project(":entity"))
        implementation(project(":usecases"))
    }
}