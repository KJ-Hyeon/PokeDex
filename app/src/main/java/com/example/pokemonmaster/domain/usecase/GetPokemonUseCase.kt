package com.example.pokemonmaster.domain.usecase

import com.example.pokemonmaster.data.remote.dto.speciespage.Result
import com.example.pokemonmaster.data.repository.PokemonWithSpecies
import com.example.pokemonmaster.domain.entity.PokemonEntity
import com.example.pokemonmaster.domain.repository.NetworkRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val networkRepository: NetworkRepository // 실제 구현부가 아닌 inferface를 주입받아야한다.!
) {
    suspend operator fun invoke(speciesUrls: List<String>): List<PokemonWithSpecies> {
        val pokemons = mutableListOf<PokemonWithSpecies>()

//        speciesUrls.forEach {url ->
//            val id = url.split("/").let {
//                if (it.size >= 2) it[it.size - 2] else "1"
//            }
//        val species = networkRepository.getPokemonSpecies(result.url)
//        val pokemon = networkRepository.getPokemon(id)
//
//
//        val name = species.names.firstOrNull { it.language.name == "ko" }?.name ?: "Error"
//        val genera = species.genera.firstOrNull { it.language.name == "ko" }?.genus ?: "Error"
////            val image = pokemon.sprites.front_default // png
//        val image = pokemon.sprites.versions.generation_v.black_white.animated.front_default //gif
//        val types = pokemon.types.map { it.type.name }
//        pokemons.add(PokemonEntity(name, id, image, genera, types))

        speciesUrls.forEach { url ->
            val species = networkRepository.getPokemonSpecies(url)
            val pokemon = networkRepository.getPokemon(species.id.toString())
            pokemons.add(PokemonWithSpecies(pokemon, species))
        }
        return pokemons
    }
}
