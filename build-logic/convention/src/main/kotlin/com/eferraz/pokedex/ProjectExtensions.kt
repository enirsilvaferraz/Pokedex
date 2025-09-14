package com.eferraz.pokedex

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.androidLibrary
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

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

internal fun Project.configureApplication(namespaceParam: String, versionNameParam: String, versionCodeParam: Int) {
    extensions.configure<ApplicationExtension> {
        namespace = namespaceParam
        defaultConfig {
            applicationId = namespaceParam
            versionName = versionNameParam
            versionCode = versionCodeParam
        }
    }
}

internal fun Project.configureLibrary(namespaceParam: String) {
    extensions.configure<KotlinMultiplatformExtension> {
        @Suppress("UnstableApiUsage")
        androidLibrary {
            namespace = "${namespaceParam}.${project.name}"
        }
    }
}

fun Project.library(scope: ProjectLibraryScope.() -> Unit) {

    with(ProjectLibraryScope) { scope() }

    configureLibrary(
        ProjectLibraryScope.namespace ?: throw IllegalStateException("Namespace is required")
    )
}

fun Project.application(scope: ProjectApplicationScope.() -> Unit) {

    with(ProjectApplicationScope) { scope() }

    configureApplication(
        ProjectApplicationScope.namespace ?: throw IllegalStateException("Namespace is required"),
        ProjectApplicationScope.versionName ?: throw IllegalStateException("Version name is required"),
        ProjectApplicationScope.versionCode ?: throw IllegalStateException("Version code is required")
    )
}