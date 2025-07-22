plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {

    explicitApi()

    androidLibrary {
        namespace = "com.eferraz.pokedex.usecases"
        compileSdk = libs.versions.android.targetSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "usecasesKit"

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
                api(project(path = ":business:entity"))
                implementation(project(path = ":adapters::repositories"))

                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)

                implementation(libs.paging.common)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}