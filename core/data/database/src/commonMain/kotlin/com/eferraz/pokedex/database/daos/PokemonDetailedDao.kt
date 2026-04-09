package com.eferraz.pokedex.database.daos

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Transaction
import com.eferraz.pokedex.database.entities.PokemonDetailedTable
import com.eferraz.pokedex.database.relationships.PokemonDetailedRelation

@Dao
internal abstract class PokemonDetailedDao : BaseDao<PokemonDetailedTable> {

    @Transaction
    @Query(
        """
        SELECT pr.pokemon_id, pr.name, pd.image, pd.type1, pd.type2, pd.species, pd.about, pd.stats
        FROM pokemon_ref pr
        INNER JOIN pokemon_detail pd ON pr.pokemon_id = pd.pokemon_id
        WHERE pr.pokemon_id = :id
        """,
    )
    abstract suspend fun get(id: Long): PokemonDetailedRelation?

    @Query(
        """
        UPDATE pokemon_detail
        SET species = :speciesId
        WHERE pokemon_id = :pokemonId
        """,
    )
    abstract suspend fun updateSpeciesFk(pokemonId: Long, speciesId: Long)
}
