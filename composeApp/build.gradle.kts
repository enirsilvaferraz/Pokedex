import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.pokedex.kmp.project)
    alias(libs.plugins.pokedex.koin.annotations)
//    alias(libs.plugins.modulegraph)
}

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        commonMain.dependencies {

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.uiUtil)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)

            implementation(libs.navigation.compose)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.paging.common)
            implementation(libs.paging.compose.common)

//            implementation(project(path = ":business:entity"))
            implementation(project(path = ":business:usecases"))
            implementation(project(path = ":adapters::repositories"))
            implementation(project(path = ":datasource:network"))
            implementation(project(path = ":datasource:database"))
        }

        commonTest.dependencies {

            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
    }
}

android {

    namespace = "com.eferraz.pokedex"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.eferraz.pokedex"
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

/*
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
 */
