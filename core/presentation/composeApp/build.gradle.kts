import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.foundation.project)
    alias(libs.plugins.foundation.library.comp)
    alias(libs.plugins.foundation.library.koin)
}

kotlin.android {
    namespace = "com.eferraz.pokedex"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    androidResources.enable = true
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {

        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.runtimeCompose)

        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor3)

        implementation(projects.domain.entity)
        implementation(projects.domain.usecases)
    }
}