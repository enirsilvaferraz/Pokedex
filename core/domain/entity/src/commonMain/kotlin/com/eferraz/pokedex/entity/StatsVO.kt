package com.eferraz.pokedex.entity

public data class StatsVO(
    val id: Long,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val total: Int = hp + attack + defense + specialAttack + specialDefense + speed,
)