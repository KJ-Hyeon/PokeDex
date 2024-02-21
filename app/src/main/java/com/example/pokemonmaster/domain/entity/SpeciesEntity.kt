package com.example.pokemonmaster.domain.entity

data class SpeciesEntity(
    val base_happiness: Int,
    val capture_rate: Int,
    val color: String,
    val egg_groups: List<String>,
    val evolution_chain: String,
    val flavor_text_entries: String,
    val gender_rate: Int,
    val genera: String,
    val hatch_counter: Int,
    val id: Int,
    val is_baby: Boolean,
    val is_legendary: Boolean,
    val is_mythical: Boolean,
    val name: String,
    val pokeUrl: String
)