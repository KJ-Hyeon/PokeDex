package com.example.pokemonmaster.domain.repository

import com.example.pokemonmaster.data.remote.dto.pokemon.Pokemon
import com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkRepository {
    suspend fun getPokemonSpecies(): com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse
    suspend fun getNextPokemonSpecies(url: String): com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse
    suspend fun getPokemon(url: String): com.example.pokemonmaster.data.remote.dto.pokemon.Pokemon
}