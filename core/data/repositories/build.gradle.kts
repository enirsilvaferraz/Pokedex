import com.eferraz.buildlogic.androidLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.project.library)
    alias(libs.plugins.foundation.library.koin)
}

androidLibrary {
    namespace = "com.eferraz.pokedex"
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