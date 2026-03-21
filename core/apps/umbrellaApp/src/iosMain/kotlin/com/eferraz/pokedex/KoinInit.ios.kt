package com.eferraz.pokedex

import org.koin.plugin.module.dsl.startKoin

public fun initKoin() {
    startKoin<MyKoinApp> {
        printLogger()
    }
}
