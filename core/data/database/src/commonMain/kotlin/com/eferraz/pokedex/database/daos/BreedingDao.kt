package com.eferraz.pokedex.database.daos

import androidx.room3.Dao
import com.eferraz.pokedex.database.entities.BreedingTable

@Dao
internal interface BreedingDao : BaseDao<BreedingTable>
