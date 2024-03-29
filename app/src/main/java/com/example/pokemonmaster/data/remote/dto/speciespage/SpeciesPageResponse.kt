package com.example.pokemonmaster.data.remote.dto.speciespage

data class SpeciesPageResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)