package com.example.pokemonmaster.data.remote.dto.pokemon

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)