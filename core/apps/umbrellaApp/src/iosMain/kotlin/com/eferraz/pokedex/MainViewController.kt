package com.eferraz.pokedex

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

public fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController { App() }
}
