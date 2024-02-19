package com.example.pokemonmaster.domain.entity

data class PokemonEntity(
    val name: String?,
    val number: String?,
    val image: String?,
    val genera: String?,
    val type: List<String>?
)
