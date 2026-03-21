package com.eferraz.pokedex

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

public fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Pokedex",
        ) {
            App()
        }
    }
}
