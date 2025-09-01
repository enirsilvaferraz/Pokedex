package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import com.eferraz.pokedex.database.entities.BreedingEggGroupCrossRef

@Dao
internal interface BreedingWithEggGroupsDao : BaseDao<BreedingEggGroupCrossRef>