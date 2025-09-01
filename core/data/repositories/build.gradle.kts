import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.koin)
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(project(":entity"))
        implementation(project(":usecases"))
        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.paging.common)
    }
}