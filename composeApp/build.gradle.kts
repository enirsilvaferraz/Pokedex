import dev.iurysouza.modulegraph.LinkText
import dev.iurysouza.modulegraph.Orientation
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.modulegraph)
    alias(libs.plugins.ksp)
}

kotlin {

    explicitApi()

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

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)

            implementation(libs.material.icons.core)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            api(libs.koin.annotations)

            implementation(libs.navigation.compose)

            implementation(libs.paging.common)
            implementation(libs.paging.compose.common)

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

    // KSP Common sourceSet
    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
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

// KSP Tasks
dependencies {
    debugImplementation(compose.uiTooling)
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
    arg("KOIN_LOG_TIMES","true")
}

// Trigger Common Metadata Generation from Native tasks
project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
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
