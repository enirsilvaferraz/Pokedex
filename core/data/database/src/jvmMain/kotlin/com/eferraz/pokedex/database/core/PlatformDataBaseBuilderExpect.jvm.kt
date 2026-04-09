package com.eferraz.pokedex.database.core

import androidx.room3.Room
import androidx.room3.RoomDatabase
import java.io.File

internal actual object PlatformDataBaseBuilder : DataBaseBuilder {

    actual override fun buildPlatform(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), databaseName())
        return Room.databaseBuilder<AppDatabase>(name = dbFile.absolutePath)
    }
}
