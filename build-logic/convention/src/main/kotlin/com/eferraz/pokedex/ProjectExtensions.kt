package com.eferraz.pokedex

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.isComposeModule(): Boolean =
    pluginManager.hasPlugin(libs.findPlugin("compose-multiplatform").get().get().pluginId)

internal fun Project.isRoomModule(): Boolean =
    pluginManager.hasPlugin(libs.findPlugin("room").get().get().pluginId)