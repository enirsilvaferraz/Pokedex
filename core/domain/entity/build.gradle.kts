import com.eferraz.buildlogic.library

plugins {
    alias(libs.plugins.foundation.kmp.library)
    alias(libs.plugins.foundation.koin)
}

library {
    namespace = "com.eferraz.pokedex"
}