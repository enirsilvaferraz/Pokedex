package com.eferraz.pokedex.repositories

import app.cash.paging.PagingSource
import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.PokemonLightVO
import com.eferraz.pokedex.entity.PokemonVO

public abstract class PokemonListRepository : PagingSource<Int, PokemonLightVO>()