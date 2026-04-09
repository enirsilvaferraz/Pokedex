package com.eferraz.pokedex.database.daos

import androidx.room3.Dao
import androidx.room3.Query
import com.eferraz.pokedex.database.entities.PokemonSummaryTable
import com.eferraz.pokedex.database.views.PokemonSummaryView
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class PokemonSummaryDao : BaseDao<PokemonSummaryTable> {

    @Query("SELECT * FROM PokemonSummaryView ORDER BY id ASC LIMIT :limit OFFSET :offset")
    abstract fun get(limit: Int, offset: Int): Flow<List<PokemonSummaryView>>
}
