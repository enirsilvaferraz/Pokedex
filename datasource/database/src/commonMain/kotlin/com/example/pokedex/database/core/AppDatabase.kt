package com.example.pokedex.database.core

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.database.daos.PokemonDao
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.entities.PokemonTypeTable
import com.example.pokedex.database.entities.TypeTable

@Database(
    entities = [
        PokemonTable::class,
        PokemonTypeTable::class,
        TypeTable::class
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}