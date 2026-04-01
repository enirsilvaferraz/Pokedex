package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import com.eferraz.pokedex.database.entities.AboutAbilitiesCrossRef

@Dao
internal interface AboutWithAbilitiesDao : BaseDao<AboutAbilitiesCrossRef>
