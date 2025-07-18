package com.eferraz.pokedex

import androidx.compose.ui.window.ComposeUIViewController
import com.eferraz.pokedex.core.App
import platform.UIKit.UIViewController

public fun MainViewController(): UIViewController = ComposeUIViewController { App() }