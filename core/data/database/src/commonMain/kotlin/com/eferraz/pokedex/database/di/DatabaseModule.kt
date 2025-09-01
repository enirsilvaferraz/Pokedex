package com.eferraz.pokedex.database.di

import com.eferraz.pokedex.database.core.AppDatabase
import com.eferraz.pokedex.database.core.PlatformDataBaseBuilder
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.eferraz.pokedex.database")
public class DatabaseModule {

    @Single
    internal fun provideDatabase(): AppDatabase = PlatformDataBaseBuilder.build()

    @Single
    internal fun provideAbilityDao(database: AppDatabase) = database.abilityDao()

    @Single
    internal fun provideAboutDao(database: AppDatabase) = database.aboutDao()

    @Single
    internal fun provideAboutWithAbilitiesDao(database: AppDatabase) = database.aboutWithAbilitiesDao()

    @Single
    internal fun provideBreedingDao(database: AppDatabase) = database.breedingDao()

    @Single
    internal fun provideBreedingWithEggGroupsDao(database: AppDatabase) = database.breedingWithEggGroupsDao()

    @Single
    internal fun provideEggGroupDao(database: AppDatabase) = database.eggGroupDao()

    @Single
    internal fun provideMoveDao(database: AppDatabase) = database.moveDao()

    @Single
    internal fun providePokemonDao(database: AppDatabase) = database.pokemonDao()

    @Single
    internal fun providePokemonWithMovesDao(database: AppDatabase) = database.pokemonWithMovesDao()

    @Single
    internal fun provideStatsDao(database: AppDatabase) = database.statsDao()

    @Single
    internal fun provideTypeDao(database: AppDatabase) = database.typeDao()
}