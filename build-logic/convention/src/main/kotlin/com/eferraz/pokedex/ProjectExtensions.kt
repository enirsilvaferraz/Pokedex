package com.eferraz.pokedex

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val VersionCatalog.plugins: CatalogDefinitions.Plugins
    get() = CatalogDefinitions.Plugins(this)

internal val VersionCatalog.libraries: CatalogDefinitions.Libraries
    get() = CatalogDefinitions.Libraries(this)

internal val VersionCatalog.bundles: CatalogDefinitions.Bundles
    get() = CatalogDefinitions.Bundles(this)

internal val VersionCatalog.versions: CatalogDefinitions.Versions
    get() = CatalogDefinitions.Versions(this)