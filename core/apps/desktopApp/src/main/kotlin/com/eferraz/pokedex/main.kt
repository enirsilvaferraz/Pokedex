package com.eferraz.pokedex

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

public fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Pokedex",
            state = rememberWindowState(
                size = DpSize(width = 390.dp, height = 844.dp),
            ),
        ) {
            App()
        }
    }
}
