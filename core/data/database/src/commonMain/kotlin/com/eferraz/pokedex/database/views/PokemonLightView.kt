package com.eferraz.pokedex.database.views

import androidx.room.DatabaseView

@DatabaseView(
    """
        SELECT p.pokemon_id AS id, p.name AS name, p.image AS image, t1.name AS type1, t2.name AS type2
        FROM pokemon p 
        LEFT JOIN type t1 ON p.type1 = t1.type_id 
        LEFT JOIN type t2 ON p.type2 = t2.type_id
        """
)
internal data class PokemonLightView(
    val id: Long,
    val name: String,
    val image: String,
    val type1: String,
    val type2: String? = null,
)