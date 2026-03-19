import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    kotlin("jvm")
}

dependencies {
    implementation(projects.apps.umbrellaApp)
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "com.eferraz.pokedex.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.eferraz.pokedex"
            packageVersion = "1.0.0"
        }
    }
}
