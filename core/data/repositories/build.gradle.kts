import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.project)
    alias(libs.plugins.foundation.library.koin)
}

kotlin.android {
    namespace = "com.eferraz.repository"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(projects.entity)
        implementation(projects.usecases)

        implementation(libs.kotlinx.coroutines.core)
        implementation(libs.paging.common)
    }
}