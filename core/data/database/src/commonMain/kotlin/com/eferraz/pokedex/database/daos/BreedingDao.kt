package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import com.eferraz.pokedex.database.entities.BreedingTable

@Dao
internal interface BreedingDao : BaseDao<BreedingTable>
