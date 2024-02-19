package com.example.pokemonmaster.data.remote.retrofit

import com.example.pokemonmaster.data.remote.dto.pokemon.PokemonResponse
import com.example.pokemonmaster.data.remote.dto.species.SpeciesResponse
import com.example.pokemonmaster.data.remote.dto.speciespage.SpeciesPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokeService {

    @GET
    suspend fun getPokemonSpeciesPage(
        @Url url: String
    ): SpeciesPageResponse

    @GET
    suspend fun getPokemonSpecies(
        @Url url: String // url 자체를 요청을 보냄
    ): SpeciesResponse

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): PokemonResponse
}