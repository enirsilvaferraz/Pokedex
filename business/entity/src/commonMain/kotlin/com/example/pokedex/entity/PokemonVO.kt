package com.example.pokedex.entity

data class PokemonVO(
    val id: Long,
    val name: String,
    val image: String = "",
    val type1: TypeVO? = null,
    val type2: TypeVO? = null,

//    val description: String,
//    val weightInKg: Float,
//    val heightInMeters: Float,
//    val category: String,
//    val abilities: List<String>,
//    val genderRatioMale: Float, // e.g., 0.875 for 87.5% male
//    val baseStats: List<PokemonStatVo>,
//    val primaryColor: Color
)