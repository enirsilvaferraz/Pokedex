package com.eferraz.pokedex.entity

data class PokemonVO(
    val id: Long,
    val name: String,
    val image: String,
    val type1: TypeVO,
    val type2: TypeVO? = null,

    val description: String,
    val weight: Float,
    val height: Float,
    val category: String,
//    val abilities: List<String>,
    val genderRatioMale: Float, // e.g., 0.875 for 87.5% male
//    val baseStats: List<StatVO>,
    val primaryColor: String,
) {

    fun types() = listOfNotNull(type1, type2)
}