package com.eferraz.pokedex.ui.detail.data

import androidx.compose.ui.graphics.Color
import com.eferraz.pokedex.core.params.PokemonSummaryParam
import com.eferraz.pokedex.helpers.capitalizeName
import com.eferraz.pokedex.helpers.formatID
import com.eferraz.pokedex.helpers.toBackground

internal data class PokemonDetailStructureView(
    val id: String,
    val title: String,
    val background: Color,
    val onBackground: Color,
) {

    constructor(ref: PokemonSummaryParam) : this(
        id = ref.id.formatID(),
        title = ref.name.capitalizeName(),
        background = ref.type1.toBackground(),
        onBackground = Color.White // todo ver cor
    )
}