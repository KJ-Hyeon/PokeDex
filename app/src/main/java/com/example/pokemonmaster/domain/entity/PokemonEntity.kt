package com.example.pokemonmaster.domain.entity

import com.example.pokemonmaster.data.remote.dto.pokemon.Ability
import com.example.pokemonmaster.data.remote.dto.pokemon.Cries
import com.example.pokemonmaster.data.remote.dto.pokemon.Form
import com.example.pokemonmaster.data.remote.dto.pokemon.GameIndice
import com.example.pokemonmaster.data.remote.dto.pokemon.Move
import com.example.pokemonmaster.data.remote.dto.pokemon.Species
import com.example.pokemonmaster.data.remote.dto.pokemon.Sprites
import com.example.pokemonmaster.data.remote.dto.pokemon.Stat
import com.example.pokemonmaster.data.remote.dto.pokemon.Type

data class PokemonEntity(
    val abilities: List<String>,
    val base_experience: Int,
    val cries: String,
    val forms: String,
    val height: Int,
    val weight: Int,
    val id: Int,
    val location_area_encounters: String,
    val name: String, // 영문이름
    val order: Int,
    val sprites: String, //  이미자, 추후에 List<String>으로 변경해서 여러 이미지 추출
    val types: List<String>,
)
