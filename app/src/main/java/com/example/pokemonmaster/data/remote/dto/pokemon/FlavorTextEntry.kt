package com.example.pokemonmaster.data.remote.dto.pokemon

data class FlavorTextEntry(
    val flavor_text: String,
    val language: com.example.pokemonmaster.data.remote.dto.pokemon.Language,
    val version: com.example.pokemonmaster.data.remote.dto.pokemon.Version
)