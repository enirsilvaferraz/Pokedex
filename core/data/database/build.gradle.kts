import com.eferraz.pokedex.library
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.room)
    alias(libs.plugins.foundation.koin)
}

library {
    namespace = "com.eferraz.pokedex"
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {

        implementation(project(":entity"))
        implementation(project(":repositories"))

        implementation(libs.paging.common)
    }
}