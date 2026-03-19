import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.foundation.project)
    alias(libs.plugins.foundation.library.koin)
}

koinCompiler {
    // `usecases` não depende de `repositories` (pra evitar ciclos), então o Koin compiler
    // não consegue validar o grafo completo em tempo de compilação. Em runtime, o
    // `MyKoinApp` carrega o `RepositoryModule`.
    compileSafety = false
}

kotlin.android {
    namespace = "com.eferraz.usecases"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
}

kotlin {

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {

        api(projects.domain.entity)

        implementation(libs.paging.common)
    }
}