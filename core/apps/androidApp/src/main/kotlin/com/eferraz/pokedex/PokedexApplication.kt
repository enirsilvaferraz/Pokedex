package com.eferraz.pokedex

import android.app.Application

public class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}
