package com.eferraz.pokedex.database.core

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

internal actual object PlatformDataBaseBuilder : DataBaseBuilder {

    actual override fun buildPlatform(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), databaseName())
        return Room.databaseBuilder<AppDatabase>(name = dbFile.absolutePath)
    }
}