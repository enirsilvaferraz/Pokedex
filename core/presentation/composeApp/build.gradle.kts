import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.foundation.compose)
    alias(libs.plugins.foundation.kmp.application)
    alias(libs.plugins.foundation.koin)
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(project(":entity"))
        implementation(project(":usecases"))
        implementation(project(":repositories"))
        implementation(project(":network"))
        implementation(project(":database"))
        implementation(project(":pokemon-list"))
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}