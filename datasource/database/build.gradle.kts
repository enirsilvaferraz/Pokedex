plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {

    explicitApi()

    androidLibrary {
        namespace = "com.example.pokedex.database"
        compileSdk = libs.versions.android.targetSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "databaseKit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcfName
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {

        commonMain {

            kotlin.srcDir("build/generated/ksp/metadata")

            dependencies {

                implementation(project(path = ":business:entity"))
                implementation(project(path = ":adapters:repositories"))

                implementation(libs.kotlin.stdlib)

                // Koin
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)

                // Room
                implementation(libs.room.runtime)
                implementation(libs.sqlite.bundled)

                implementation(libs.paging.common)

            }
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    listOf("kspAndroid", "kspIosSimulatorArm64", "kspIosX64", "kspIosArm64").forEach {
        add(it, libs.room.compiler)
    }
}