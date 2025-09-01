import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.koin)
    alias(libs.plugins.foundation.ktor)
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(project(":entity"))
        implementation(project(":repositories"))
    }
}