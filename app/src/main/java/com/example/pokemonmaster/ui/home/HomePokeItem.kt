package com.example.pokemonmaster.ui.home

// Home에서 보여줄 포켓몬 dataclass
data class HomePokeItem (
    val name: String?,
    val number: String?,
    val image: String?,
    val genera: String?,
    val type: List<String>?
)