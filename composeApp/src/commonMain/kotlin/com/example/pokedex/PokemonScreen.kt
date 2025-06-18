package com.example.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun PokemonRoute() {
    PokemonScreen(
        PokemonVO(
            color = listOf(Color.Green, Color.Red, Color.Blue).random(),
            id = 1,
            name = "SampleMon",
            types = listOf("Type A", "Type B")
        )
    )
}

@Composable
private fun PokemonScreen(model: PokemonVO) {

}