plugins {
    alias(libs.plugins.pokedex.kmp.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
//    alias(libs.plugins.pokedex.koin.annotations) // Aguardando resolução do problema https://github.com/InsertKoinIO/koin/issues/2174
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {

                implementation(project(path = ":business:entity"))
                implementation(project(path = ":adapters:repositories"))

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