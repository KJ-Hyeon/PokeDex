package com.example.pokemonmaster.data.remote.retrofit

import com.example.pokemonmaster.data.remote.dto.pokemon.Pokemon
import com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeService {

    @GET("pokemon-species")
    suspend fun getPokemonSpecies(): com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse

    @GET
    suspend fun getNextPokemonSpecies(
        @Url url: String
    ): com.example.pokemonmaster.data.remote.dto.species.PokemonSpeciesResponse

    @GET
    suspend fun getPokemon(
        @Url url: String // url 자체를 요청을 보냄
    ): com.example.pokemonmaster.data.remote.dto.pokemon.Pokemon
}