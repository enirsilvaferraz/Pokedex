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

        register("pokedex-kmp-application") {
            id = libs.plugins.pokedex.kmp.application.get().pluginId
            implementationClass = "KmpApplicationPlugin"
        }

        register("pokedex-kmp-library") {
            id = libs.plugins.pokedex.kmp.library.get().pluginId
            implementationClass = "KmpLibraryPlugin"
        }

        register("pokedex-cmp-library") {
            id = libs.plugins.pokedex.cmp.library.get().pluginId
            implementationClass = "CmpLibraryPlugin"
        }

        register("pokedex-koin-annotations") {
            id = libs.plugins.pokedex.koin.annotations.get().pluginId
            implementationClass = "PokedexKoinPlugin"
        }

        register("pokedex-room") {
            id = libs.plugins.pokedex.room.get().pluginId
            implementationClass = "PokedexRoomPlugin"
        }
    }
}
