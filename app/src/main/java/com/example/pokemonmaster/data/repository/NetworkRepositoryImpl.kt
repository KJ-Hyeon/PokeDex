package com.example.pokemonmaster.data.repository

import com.example.pokemonmaster.data.remote.dto.pokemon.PokemonResponse
import com.example.pokemonmaster.data.remote.dto.species.SpeciesResponse
import com.example.pokemonmaster.data.remote.retrofit.PokeService
import com.example.pokemonmaster.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokeService
): NetworkRepository {
    override suspend fun getPokemonSpeciesPage(url: String) = withContext(Dispatchers.IO){
        remoteDataSource.getPokemonSpeciesPage(url)
    }

    override suspend fun getPokemonSpecies(url: String): SpeciesResponse = withContext(Dispatchers.IO){
        remoteDataSource.getPokemonSpecies(url)
    }

    override suspend fun getPokemon(id: String) = withContext(Dispatchers.IO){
        remoteDataSource.getPokemon(id)
    }
}