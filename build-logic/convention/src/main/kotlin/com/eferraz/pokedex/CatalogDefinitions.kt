package com.eferraz.pokedex

import org.gradle.api.artifacts.VersionCatalog

internal interface CatalogDefinitions {

    class Plugins(libs: VersionCatalog) {

        val android_application = libs.find("android.application")
        val compose_compiler = libs.find("compose-compiler")
        val compose_multiplatform = libs.find("compose-multiplatform")
        val ksp = libs.find("ksp")
        val kotlin_multiplatform = libs.find("kotlin-multiplatform")
        val kotlin_multiplatform_library = libs.find("android-kotlin-multiplatform-library")
        val room = libs.find("room")

        private fun VersionCatalog.find(alias: String): String = findPlugin(alias).get().get().pluginId
    }

    class Libraries(libs: VersionCatalog) {

        val kotlin_stdlib = libs.find("kotlin-stdlib")
        val kotlin_test = libs.find("kotlin-test")
        val koin_annotations = libs.find("koin-annotations")
        val koin_bom = libs.find("koin-bom")
        val koin_compose = libs.find("koin-compose")
        val koin_test = libs.find("koin-test")
        val koin_test_junit4 = libs.find("koin-test-junit4")
        val koin_compose_viewmodel = libs.find("koin-compose-viewmodel")
        val koin_compose_viewmodel_navigation = libs.find("koin-compose-viewmodel-navigation")
        val koin_core = libs.find("koin-core")
        val koin_ksp_compiler = libs.find("koin-ksp-compiler")
        val room_compiler = libs.find("room-compiler")
        val room_runtime = libs.find("room-runtime")
        val sqlite_bundled = libs.find("sqlite-bundled")

        private fun VersionCatalog.find(alias: String) = findLibrary(alias).get()
    }

    class Versions(libs: VersionCatalog) {

        val compileSdk = libs.find("android.compileSdk") // Keep original: android.compileSdk
        val minSdk = libs.find("android.minSdk") // Keep original: android.minSdk
        val targetSdk = libs.find("android.targetSdk") // Keep original: android.targetSdk

        private fun VersionCatalog.find(alias: String) = findVersion(alias).get().requiredVersion.toInt()
    }
}
