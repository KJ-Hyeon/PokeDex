package com.example.pokemonmaster.domain.entity

import com.example.pokemonmaster.data.remote.dto.speciespage.Result

data class SpeciesPageEntity (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<String>
)