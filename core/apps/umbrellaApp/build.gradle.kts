import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.project)
    alias(libs.plugins.foundation.library.comp)
    alias(libs.plugins.foundation.library.koin)
}

kotlin {

    compilerOptions {
        freeCompilerArgs.add("-Xskip-prerelease-check")
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(projects.features.composeApp)
        implementation(projects.domain.entity)
        implementation(projects.domain.usecases)
        implementation(projects.data.repositories)
        implementation(projects.data.network)
        implementation(projects.data.database)
    }
}

kotlin.android {
    namespace = "com.eferraz.pokedex.umbrella"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    androidResources.enable = true
}
