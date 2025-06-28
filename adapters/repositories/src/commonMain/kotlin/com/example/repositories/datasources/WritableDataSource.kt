package com.example.repositories.datasources

public interface WritableDataSource<T> {
    suspend fun insert(entity: T)
}