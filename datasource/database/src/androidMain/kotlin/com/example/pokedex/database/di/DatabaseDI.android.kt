package com.example.pokedex.database.di

import com.example.pokedex.database.core.AppDatabase
import com.example.pokedex.database.core.getAppDatabase
import com.example.pokedex.database.core.getDatabaseBuilder
import org.koin.core.module.Module

internal actual fun Module.platformModule() {
    this.single<AppDatabase> {
        getAppDatabase(getDatabaseBuilder(this.get()))
    }
}