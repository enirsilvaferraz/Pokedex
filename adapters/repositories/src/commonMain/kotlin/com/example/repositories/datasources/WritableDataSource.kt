package com.example.repositories.datasources

public interface WritableDataSource<T> {

    suspend fun insert(entities: List<T>)
}