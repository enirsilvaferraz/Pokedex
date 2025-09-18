package com.eferraz.pokedex.database.core

import androidx.room.RoomDatabase

internal expect object PlatformDataBaseBuilder : DataBaseBuilder {

    override fun buildPlatform(): RoomDatabase.Builder<AppDatabase>
}