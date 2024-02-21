package com.example.pokemonmaster.di

import com.example.pokemonmaster.data.repository.NetworkRepositoryImpl
import com.example.pokemonmaster.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNetworkRepository(repository: NetworkRepositoryImpl): NetworkRepository
}