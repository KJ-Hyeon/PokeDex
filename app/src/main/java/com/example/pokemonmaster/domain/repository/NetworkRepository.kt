package com.example.pokemonmaster.domain.repository

import com.example.pokemonmaster.data.remote.dto.pokemon.PokemonResponse
import com.example.pokemonmaster.data.remote.dto.species.SpeciesResponse
import com.example.pokemonmaster.data.remote.dto.speciespage.SpeciesPageResponse

interface NetworkRepository {
    suspend fun getPokemonSpeciesPage(url: String): SpeciesPageResponse
    suspend fun getPokemonSpecies(url: String): SpeciesResponse
    suspend fun getPokemon(id: String): PokemonResponse
}