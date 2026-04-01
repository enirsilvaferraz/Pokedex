package com.eferraz.pokedex.network.responses.species

import com.eferraz.pokedex.entity.detail.Breeding
import com.eferraz.pokedex.entity.detail.EggGroup
import com.eferraz.pokedex.entity.detail.Species
import com.eferraz.pokedex.network.responses.NamedApiResource
import com.eferraz.pokedex.network.responses.pokedex.PokedexName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonSpeciesDetail(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("gender_rate")
    val genderRate: Int,
    @SerialName("capture_rate")
    val captureRate: Int,
    @SerialName("base_happiness")
    val baseHappiness: Int?,
    @SerialName("is_baby")
    val isBaby: Boolean,
    @SerialName("is_legendary")
    val isLegendary: Boolean,
    @SerialName("is_mythical")
    val isMythical: Boolean,
    @SerialName("hatch_counter")
    val hatchCounter: Int?,
    @SerialName("has_gender_differences")
    val hasGenderDifferences: Boolean,
    @SerialName("forms_switchable")
    val formsSwitchable: Boolean,
    @SerialName("growth_rate")
    val growthRate: NamedApiResource,
    @SerialName("pokedex_numbers")
    val pokedexNumbers: List<PokemonSpeciesDexEntry>,
    @SerialName("egg_groups")
    val eggGroups: List<NamedApiResource>,
    @SerialName("color")
    val color: NamedApiResource,
    @SerialName("shape")
    val shape: NamedApiResource?,
    @SerialName("evolves_from_species")
    val evolvesFromSpecies: NamedApiResource?,
    @SerialName("evolution_chain")
    val evolutionChain: NamedApiResource?,
    @SerialName("habitat")
    val habitat: NamedApiResource?,
    @SerialName("generation")
    val generation: NamedApiResource,
    @SerialName("names")
    val names: List<PokedexName>,
    @SerialName("pal_park_encounters")
    val palParkEncounters: List<PalParkEncounterArea>,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<FlavorText>,
    @SerialName("genera")
    val genera: List<Genus>,
    @SerialName("varieties")
    val varieties: List<PokemonSpeciesVariety>,
) {

    internal fun toModel() = Species(
        id = id.toLong(),
        description = flavorTextEntries.firstOrNull { it.language.name == "en" }?.flavorText.cleanUp(),
        species = genera.firstOrNull { it.language.name == "en" }?.genus.orEmpty(),
        category = shape?.name.orEmpty(),
        breeding = Breeding(
            id = id.toLong(),
            genderRatio = genderRate.toFloat(),
            eggGroups = eggGroups.map { EggGroup(id = it.getId(), name = it.name.orEmpty()) },
        ),
    )

    private fun String?.cleanUp() =
        this?.replace("\n", " ")?.replace(Regex("\\f"), " ").orEmpty()
}
