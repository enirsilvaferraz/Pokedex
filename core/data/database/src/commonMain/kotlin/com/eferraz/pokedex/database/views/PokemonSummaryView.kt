package com.eferraz.pokedex.database.views

import androidx.room3.DatabaseView
import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.entity.summary.PokemonSummary

@DatabaseView(
    viewName = "PokemonSummaryView",
    value = """
        SELECT
            r.pokemon_id AS id,
            r.name AS name,
            t1.type_id AS type1Id,
            t1.name AS type1Name,
            t2.type_id AS type2Id,
            t2.name AS type2Name
        FROM pokemon_ref r
        LEFT JOIN pokemon_detail d ON r.pokemon_id = d.pokemon_id
        LEFT JOIN type t1 ON d.type1 = t1.type_id
        LEFT JOIN type t2 ON d.type2 = t2.type_id
        """
)
internal data class PokemonSummaryView(
    val id: Long,
    val name: String,
    val type1Id: Long?,
    val type1Name: String?,
    val type2Id: Long?,
    val type2Name: String?,
) {

    fun toModel() =
        PokemonSummary(
            id = id,
            name = name,
            type1 = type1Id?.let { Type(id = it, name = type1Name.orEmpty()) },
            type2 = type2Id?.let { Type(id = it, name = type2Name.orEmpty()) },
        )
}
