package com.eferraz.pokedex

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.eferraz.pokedex.core.App

public fun main(): Unit = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pokedex",
    ) {
        App()
    }
}