package com.eferraz.pokedex.database.core

import androidx.room.RoomDatabaseConstructor

internal expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
