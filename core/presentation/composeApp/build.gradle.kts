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

    compilerOptions {
        freeCompilerArgs.add("-Xreturn-value-checker=check")
        freeCompilerArgs.add("-Xexplicit-backing-fields")
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {

        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.runtimeCompose)

        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor3)

        implementation(libs.paging.common)
        implementation(libs.paging.compose)

        implementation(projects.domain.entity)
        implementation(projects.domain.usecases)
    }
}

dependencies {
    "androidRuntimeClasspath"(libs.androidx.ui.tooling)
}