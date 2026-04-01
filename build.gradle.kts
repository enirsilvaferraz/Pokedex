plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.multiplatform.library) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.android.lint) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.koin.compiler) apply false
    // Resolução do id io.gitlab.arturbosch.detekt para o [FoundationDetektPlugin].
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.foundation.detekt) apply true
}

subprojects {
    configurations.configureEach {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.skiko") {
                useVersion(libs.versions.skiko.get())
                because("Alinha Skiko com Compose Multiplatform (evita conflito transitivo Coil → Skiko antigo).")
            }
        }
    }
}