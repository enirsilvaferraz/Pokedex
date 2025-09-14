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
        val kotlin_serialization = libs.find("kotlin-serialization")

        private fun VersionCatalog.find(alias: String): String = findPlugin(alias).get().get().pluginId
    }

    class Libraries(libs: VersionCatalog) {

        val kotlin_stdlib = libs.find("kotlin-stdlib")
        val kotlin_test = libs.find("kotlin-test")
        val koin_bom = libs.find("koin-bom")

        private fun VersionCatalog.find(alias: String) = findLibrary(alias).get()
    }

    class Versions(libs: VersionCatalog) {

        val compileSdk = libs.find("android.compileSdk")
        val minSdk = libs.find("android.minSdk")
        val targetSdk = libs.find("android.targetSdk")

        private fun VersionCatalog.find(alias: String) = findVersion(alias).get().requiredVersion.toInt()
    }

    class Bundles(libs: VersionCatalog) {

        val ktor_common = libs.find("ktor-common")
        val ktor_android = libs.find("ktor-android")
        val ktor_ios = libs.find("ktor-ios")

        val koin_common = libs.find("koin-common")
        val koin_common_compose = libs.find("koin-common-compose")
        val koin_common_compiler = libs.find("koin-common-compiler")
        val koin_common_test = libs.find("koin-common-test")

        val room_common = libs.find("room-common")
        val room_common_compiler = libs.find("room-common-compiler")

        val navigation_common = libs.find("navigation-common")

        private fun VersionCatalog.find(alias: String) = findBundle(alias).get()
    }
}
