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

    versionCatalogs {
        create("libs") {
            from(files("build-logic/gradle/libs.versions.toml"))
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":apps:androidApp")
include(":apps:desktopApp")
include(":apps:umbrellaApp")
project(":apps").projectDir = File(settingsDir, "core/apps")
project(":apps:androidApp").projectDir = File(settingsDir, "core/apps/androidApp")
project(":apps:desktopApp").projectDir = File(settingsDir, "core/apps/desktopApp")
project(":apps:umbrellaApp").projectDir = File(settingsDir, "core/apps/umbrellaApp")

include(":features:composeApp")
project(":features").projectDir = File(settingsDir, "core/presentation")
project(":features:composeApp").projectDir = File(settingsDir, "core/presentation/composeApp")

include(":domain:usecases")
include(":domain:entity")
project(":domain").projectDir = File(settingsDir, "core/domain")
project(":domain:usecases").projectDir = File(settingsDir, "core/domain/usecases")
project(":domain:entity").projectDir = File(settingsDir, "core/domain/entity")

include(":data:repositories")
include(":data:network")
include(":data:database")
project(":data").projectDir = File(settingsDir, "core/data")
project(":data:repositories").projectDir = File(settingsDir, "core/data/repositories")
project(":data:network").projectDir = File(settingsDir, "core/data/network")
project(":data:database").projectDir = File(settingsDir, "core/data/database")
