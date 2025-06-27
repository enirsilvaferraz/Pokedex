package com.example.pokedex.database.di

import com.example.pokedex.database.core.AppDatabase
import com.example.pokedex.database.core.getDatabaseBuilder
import com.example.pokedex.database.core.getAppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun Module.platformModule() {
    this.single<AppDatabase> {
        getAppDatabase(getDatabaseBuilder())
    }
}