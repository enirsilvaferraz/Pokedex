plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.pokedex.koin.annotations)
}

kotlin {

    explicitApi()

    androidLibrary {
        namespace = "com.eferraz.repositories"
        compileSdk = libs.versions.android.targetSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "repositoriesKit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {

        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.paging.common)

                implementation(project(path = ":business:entity"))
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}