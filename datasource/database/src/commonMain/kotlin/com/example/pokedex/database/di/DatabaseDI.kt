package com.example.pokedex.database.di

import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseDI {

    operator fun invoke() = module {

        // Platform specific modules
        platformModule()
    }
}

internal expect fun Module.platformModule()