package com.eferraz.pokedex.usecases.repositories

import app.cash.paging.PagingSource
import com.eferraz.pokedex.entity.PokemonLightVO

public abstract class PokemonListRepository : PagingSource<Int, PokemonLightVO>()