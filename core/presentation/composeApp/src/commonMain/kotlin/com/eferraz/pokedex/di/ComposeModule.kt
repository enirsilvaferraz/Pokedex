package com.eferraz.pokedex.di

import com.eferraz.pokedex.usecases.di.UseCaseModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [UseCaseModule::class])
@ComponentScan("com.eferraz.pokedex")
public class ComposeModule
