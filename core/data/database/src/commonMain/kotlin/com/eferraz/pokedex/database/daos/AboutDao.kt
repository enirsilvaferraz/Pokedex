package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import com.eferraz.pokedex.database.entities.AboutTable

@Dao
internal interface AboutDao : BaseDao<AboutTable>