package com.eferraz.pokedex.database.di

import com.eferraz.pokedex.database.datasources.PokemonDataSourceDB
import com.eferraz.pokedex.database.core.AppDatabase
import com.eferraz.pokedex.database.core.PlatformDataBaseBuilder
import com.eferraz.pokedex.repositories.datasources.PokemonDataSource
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

public object DatabaseDI {

    public operator fun invoke(): Module = module {

        /**
         * Database Platform specific modules
         */

        single<AppDatabase> { PlatformDataBaseBuilder.build() }

        /**
         * DAOs
         */

        single { get<AppDatabase>().pokemonDao() }
        single { get<AppDatabase>().typeDao() }

        /**
         * Data Sources
         */

        factoryOf(::PokemonDataSourceDB) bind PokemonDataSource.Database::class
    }
}