package com.example.pokemonmaster.data.remote.dto.species

data class PokemonSpeciesResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val pokemonSpecies: List<com.example.pokemonmaster.data.remote.dto.species.PokemonSpecies>
)