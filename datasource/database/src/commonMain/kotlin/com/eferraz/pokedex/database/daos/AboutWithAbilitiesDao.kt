package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.eferraz.pokedex.database.entities.AboutAbilitiesCrossRef

@Dao
internal interface AboutWithAbilitiesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: AboutAbilitiesCrossRef)
}
