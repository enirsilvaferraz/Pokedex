import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.foundation.project)
    alias(libs.plugins.foundation.library.koin)
    alias(libs.plugins.foundation.library.ktor)
}

kotlin.android {
    namespace = "com.eferraz.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(projects.domain.entity)
    }
}