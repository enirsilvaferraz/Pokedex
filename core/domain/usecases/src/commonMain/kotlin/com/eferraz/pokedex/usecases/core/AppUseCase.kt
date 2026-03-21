package com.eferraz.pokedex.usecases.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

public abstract class AppUseCase<in P, out R>(
    private val context: CoroutineDispatcher,
) {

    public suspend operator fun invoke(param: P): Result<R> = withContext(context) {
        result { execute(param) }
    }

    protected abstract suspend fun execute(param: P): R

    private suspend inline fun <T> result(function: suspend () -> T): Result<T> =
        runCatching {

            val label = this::class.simpleName ?: "UnknownUseCase"
            val pair = measureTimeMillisSuspend(label) { function() }

            Result.success(pair.second)

        }.getOrElse {
            Result.failure(it)
        }
}