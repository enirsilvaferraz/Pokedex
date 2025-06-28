package com.example.repositories.di

import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named

public sealed class AppQualifiers private constructor(private val resource: String) {

    /**
     * Private functions
     */

    private fun qualifier(context: String, resource: String): StringQualifier = named("$context-$resource")

    /**
     * Public functions
     */

    public fun database(): StringQualifier = qualifier(DATASOURCE_DATABASE, resource)

    public fun network(): StringQualifier = qualifier(DATASOURCE_NETWORK, resource)

    /**
     * Sealed classes
     */

    public object Pokemon : AppQualifiers("POKEMON")


    /**
     * Constants
     */

    companion object {
        private const val DATASOURCE_NETWORK = "NETWORK"
        private const val DATASOURCE_DATABASE = "DATABASE"
    }
}