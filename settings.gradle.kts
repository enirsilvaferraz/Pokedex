rootProject.name = "Pokedex"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    @Suppress("UnstableApiUsage")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
project(":composeApp").projectDir = File(settingsDir,"core/presentation/composeApp")

include(":pokemon-list")
project(":pokemon-list").projectDir = File(settingsDir,"core/presentation/pokemon-list")

include(":entity")
project(":entity").projectDir = File(settingsDir,"core/domain/entity")

include(":usecases")
project(":usecases").projectDir = File(settingsDir,"core/domain/usecases")

include(":repositories")
project(":repositories").projectDir = File(settingsDir,"core/data/repositories")

include(":network")
project(":network").projectDir = File(settingsDir,"core/data/network")

include(":database")
project(":database").projectDir = File(settingsDir,"core/data/database")
