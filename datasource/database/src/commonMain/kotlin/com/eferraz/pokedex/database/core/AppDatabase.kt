package com.eferraz.pokedex.database.core

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.eferraz.pokedex.database.daos.PokemonDao
import com.eferraz.pokedex.database.daos.TypeDao
import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.entities.TypeTable

@Database(
    entities = [
        PokemonTable::class,
        TypeTable::class
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun typeDao(): TypeDao
}