import dev.iurysouza.modulegraph.LinkText
import dev.iurysouza.modulegraph.Orientation
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.modulegraph)
}

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val xcfName = "ComposeApp"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcfName
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation("io.coil-kt.coil3:coil-compose:3.2.0")
            implementation("io.coil-kt.coil3:coil-network-ktor3:3.2.0")

            implementation("org.jetbrains.compose.material:material-icons-core:1.7.3")

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-beta03")

            implementation(libs.kotlinx.serialization.json)

//            implementation(project(path = ":business:entity"))
            implementation(project(path = ":business:usecases"))
            implementation(project(path = ":adapters::repositories"))
            implementation(project(path = ":datasource:network"))
            implementation(project(path = ":datasource:database"))
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
    }
}

android {

    namespace = "com.example.pokedex"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.pokedex"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

moduleGraphConfig {

    heading.set("# Primary Graph - Compose 1")
    readmePath.set("${rootDir}/MERMAID.md")

    showFullPath.set(true)
    orientation.set(Orientation.TOP_TO_BOTTOM)
    linkText.set(LinkText.NONE)
    setStyleByModuleType.set(true)
    nestingEnabled.set(true)

//    graph(
//        readmePath = "${rootDir}/MERMAID.md",
//        heading = "### Module Graph - Compose",
//    ) {
//        nestingEnabled = true
//        setStyleByModuleType = true
//    }
//
//    graph(
//        readmePath = "${rootDir}/MERMAID.md",
//        heading = "### Module Graph - Entity",
//    ) {
//        nestingEnabled = true
//    }
//
//    graph(
//        readmePath = "${rootDir}/MERMAID.md",
//        heading = "### Module Graph - Repository",
//    ) {
//        nestingEnabled = true
//    }
//
//    graph(
//        readmePath = "${rootDir}/MERMAID.md",
//        heading = "### Module Graph - Network",
//    ) {
//        nestingEnabled = true
//    }
//
//    graph(
//        readmePath = "${rootDir}/MERMAID.md",
//        heading = "### Module Graph - Database",
//    ) {
//        nestingEnabled = true
//        focusedModulesRegex = ".*(database).*"
//    }
}
