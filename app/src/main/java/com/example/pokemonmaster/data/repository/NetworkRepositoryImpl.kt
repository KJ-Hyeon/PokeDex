package com.example.pokemonmaster.data.repository

import com.example.pokemonmaster.data.remote.dto.pokemon.Pokemon
import com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse
import com.example.pokemonmaster.data.remote.retrofit.PokeService
import com.example.pokemonmaster.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokeService
): NetworkRepository {
    override suspend fun getPokemonSpecies(): com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse {
        return remoteDataSource.getPokemonSpecies()
    }

    override suspend fun getNextPokemonSpecies(url: String): com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse {
        return remoteDataSource.getNextPokemonSpecies(url)
    }

    override suspend fun getPokemon(url: String): com.example.pokemonmaster.data.remote.dto.pokemon.Pokemon {
        return getPokemon(url)
    }
}