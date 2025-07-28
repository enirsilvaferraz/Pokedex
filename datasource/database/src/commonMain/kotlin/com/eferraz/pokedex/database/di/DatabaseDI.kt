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

        single { get<AppDatabase>().abilityDao() }
        single { get<AppDatabase>().aboutDao() }
        single { get<AppDatabase>().aboutWithAbilitiesDao() }
        single { get<AppDatabase>().breedingDao() }
        single { get<AppDatabase>().breedingWithEggGroupsDao() }
        single { get<AppDatabase>().eggGroupDao() }
        single { get<AppDatabase>().moveDao() }
        single { get<AppDatabase>().pokemonDao() }
        single { get<AppDatabase>().pokemonWithMovesDao() }
        single { get<AppDatabase>().statsDao() }
        single { get<AppDatabase>().typeDao() }

        /**
         * Data Sources
         */

        factoryOf(::PokemonDataSourceDB) bind PokemonDataSource.Database::class
    }
}