package com.example.repositories.datasources

import kotlinx.coroutines.flow.Flow

public interface ReadableDataSource<T> {
    suspend fun getAll(): Flow<List<T>>
}