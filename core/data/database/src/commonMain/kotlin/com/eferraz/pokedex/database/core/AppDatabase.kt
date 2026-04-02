package com.eferraz.pokedex.database.core

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.eferraz.pokedex.database.daos.AbilityDao
import com.eferraz.pokedex.database.daos.AboutDao
import com.eferraz.pokedex.database.daos.AboutWithAbilitiesDao
import com.eferraz.pokedex.database.daos.BreedingDao
import com.eferraz.pokedex.database.daos.BreedingWithEggGroupsDao
import com.eferraz.pokedex.database.daos.EggGroupDao
import com.eferraz.pokedex.database.daos.MoveDao
import com.eferraz.pokedex.database.daos.PokemonDetailedDao
import com.eferraz.pokedex.database.daos.PokemonSummaryDao
import com.eferraz.pokedex.database.daos.PokemonWithMovesDao
import com.eferraz.pokedex.database.daos.SpeciesDao
import com.eferraz.pokedex.database.daos.StatsDao
import com.eferraz.pokedex.database.daos.TypeDao
import com.eferraz.pokedex.database.entities.AbilityTable
import com.eferraz.pokedex.database.entities.AboutAbilitiesCrossRef
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.database.entities.BreedingEggGroupCrossRef
import com.eferraz.pokedex.database.entities.BreedingTable
import com.eferraz.pokedex.database.entities.EggGroupTable
import com.eferraz.pokedex.database.entities.MoveTable
import com.eferraz.pokedex.database.entities.PokemonDetailedTable
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef
import com.eferraz.pokedex.database.entities.PokemonSummaryTable
import com.eferraz.pokedex.database.entities.SpeciesTable
import com.eferraz.pokedex.database.entities.StatsTable
import com.eferraz.pokedex.database.entities.TypeTable
import com.eferraz.pokedex.database.views.PokemonSummaryView

@Suppress("TooManyFunctionsInFile")
@Database(
    entities = [
        AbilityTable::class,
        AboutAbilitiesCrossRef::class,
        AboutTable::class,
        BreedingEggGroupCrossRef::class,
        BreedingTable::class,
        EggGroupTable::class,
        MoveTable::class,
        PokemonMovesCrossRef::class,
        PokemonSummaryTable::class,
        PokemonDetailedTable::class,
        SpeciesTable::class,
        StatsTable::class,
        TypeTable::class
    ],
    views = [PokemonSummaryView::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun abilityDao(): AbilityDao

    abstract fun aboutDao(): AboutDao

    abstract fun aboutWithAbilitiesDao(): AboutWithAbilitiesDao

    abstract fun breedingDao(): BreedingDao

    abstract fun breedingWithEggGroupsDao(): BreedingWithEggGroupsDao

    abstract fun eggGroupDao(): EggGroupDao

    abstract fun moveDao(): MoveDao

    abstract fun pokemonDao(): PokemonSummaryDao

    abstract fun speciesDao(): SpeciesDao

    abstract fun pokemonDetailedDao(): PokemonDetailedDao

    abstract fun pokemonWithMovesDao(): PokemonWithMovesDao

    abstract fun statsDao(): StatsDao

    abstract fun typeDao(): TypeDao
}
