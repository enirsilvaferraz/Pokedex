package com.eferraz.pokedex.usecases.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.eferraz.pokedex.usecases")
public class UseCaseModule {

    @Factory
    public fun provideCoroutineDispatcher(): CoroutineDispatcher =
        Dispatchers.Default

    @Factory
    public fun provideMutex(): Mutex =
        Mutex()

    @Factory
    public fun provideSemaphore(): Semaphore =
        Semaphore(permits = 5)
}
