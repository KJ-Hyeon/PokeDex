package com.example.pokemonmaster.domain.repository

import com.example.pokemonmaster.data.remote.dto.pokemon.PokemonResponse
import com.example.pokemonmaster.data.remote.dto.species.SpeciesResponse
import com.example.pokemonmaster.data.remote.dto.speciespage.SpeciesPageResponse
import com.example.pokemonmaster.domain.entity.PokemonEntity
import com.example.pokemonmaster.domain.entity.SpeciesEntity
import com.example.pokemonmaster.domain.entity.SpeciesPageEntity

interface NetworkRepository {
    suspend fun getPokemonSpeciesPage(url: String): SpeciesPageEntity
    suspend fun getPokemonSpecies(url: String): SpeciesEntity
    suspend fun getPokemon(id: String): PokemonEntity
}