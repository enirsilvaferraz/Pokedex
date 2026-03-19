package com.eferraz.pokedex

import androidx.compose.runtime.Composable
import com.eferraz.pokedex.core.InternalApp
import org.koin.core.KoinApplication
import org.koin.plugin.module.dsl.startKoin

@Composable
public fun App(koinAppDeclaration: (KoinApplication.() -> Unit)? = null) {

    startKoin<MyKoinApp> {
        koinAppDeclaration?.invoke(this)
        printLogger()
    }

    InternalApp()
}
