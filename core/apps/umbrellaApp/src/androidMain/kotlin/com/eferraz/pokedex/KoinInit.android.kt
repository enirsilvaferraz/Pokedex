package com.eferraz.pokedex

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.plugin.module.dsl.startKoin

public fun initKoin(context: Context) {
    startKoin<MyKoinApp> {
        androidContext(context)
        printLogger()
    }
}
