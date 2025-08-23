plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.gradle.plugin.android.tools.common)
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.compose)
    compileOnly(libs.gradle.plugin.compose.compiler)
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.ksp)
    compileOnly(libs.gradle.plugin.room)
//    lintChecks(libs.androidx.lint.gradle)
}

gradlePlugin {

    plugins {

        libs.plugins.foundation.kmp.application.get().pluginId.let {
            register(it) {
                id = it
                implementationClass = "KmpApplicationPlugin"
            }
        }

        libs.plugins.foundation.kmp.library.get().pluginId.let {
            register(it) {
                id = it
                implementationClass = "KmpLibraryPlugin"
            }
        }

        libs.plugins.foundation.compose.get().pluginId.let {
            register(it) {
                id = it
                implementationClass = "LibraryComposePlugin"
            }
        }

        libs.plugins.foundation.koin.get().pluginId.let {
            register(it) {
                id = it
                implementationClass = "LibraryKoinPlugin"
            }
        }

        libs.plugins.foundation.room.get().pluginId.let {
            register(it) {
                id = it
                implementationClass = "LibraryRoomPlugin"
            }
        }

        libs.plugins.foundation.ktor.get().pluginId.let {
            register(it) {
                id = it
                implementationClass = "LibraryKtorPlugin"
            }
        }
    }
}
