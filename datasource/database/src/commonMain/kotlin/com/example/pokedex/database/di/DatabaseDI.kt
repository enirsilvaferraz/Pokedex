package com.example.pokedex.database.di

import com.example.pokedex.database.contracts.PokemonDataSourceDB
import com.example.pokedex.database.core.AppDatabase
import com.example.pokedex.database.core.PlatformDataBaseBuilder
import com.example.repositories.datasources.ReadableDataSource
import com.example.repositories.datasources.WritableDataSource
import com.example.repositories.di.AppQualifiers
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.binds
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

        factoryOf(::PokemonDataSourceDB, { qualifier = AppQualifiers.Pokemon.database() }) binds
                arrayOf(ReadableDataSource::class, WritableDataSource::class)
    }
}