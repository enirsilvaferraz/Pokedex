package com.eferraz.pokedex.database.daos

import androidx.room3.Dao
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef

@Dao
internal interface PokemonWithMovesDao : BaseDao<PokemonMovesCrossRef>
