package com.example.pokedex.database.helpers

import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.entities.TypeTable
import com.example.pokedex.database.relationships.PokemonAndType
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO

internal fun TypeTable.toVO() = TypeVO(
    id = id,
    name = name
)

internal fun PokemonAndType.toVO() = PokemonVO(
    id = pokemon.id,
    name = pokemon.name,
    image = pokemon.image,
    type1 = type1?.toVO(),
    type2 = type2?.toVO()
)

internal fun PokemonVO.toTable() = PokemonTable(
    id = id,
    name = name,
    image = image,
    typeID1 = type1?.id,
    typeID2 = type2?.id
)

internal fun TypeVO.toTable() = TypeTable(
    id = id,
    name = name
)
