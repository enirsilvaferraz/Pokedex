import com.eferraz.buildlogic.androidLibrary

plugins {
    alias(libs.plugins.foundation.project.library)
    alias(libs.plugins.foundation.library.koin)
}

androidLibrary {
    namespace = "com.eferraz.pokedex"
}