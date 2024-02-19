package com.example.pokemonmaster.domain.usecase

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.pokemonmaster.data.remote.dto.species.Pokemon
import com.example.pokemonmaster.data.remote.dto.speciespage.Result
import com.example.pokemonmaster.data.repository.NetworkRepositoryImpl
import com.example.pokemonmaster.domain.entity.PokemonEntity
import com.example.pokemonmaster.domain.repository.NetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val networkRepositoryImpl: NetworkRepositoryImpl
) {
    suspend operator fun invoke(speciesUrls: List<Result>): List<PokemonEntity> {
        val pokemons = mutableListOf<PokemonEntity>()

        speciesUrls.forEach {result ->
            val id = result.url.split("/").let {
                if (it.size >= 2) it[it.size - 2] else "1"
            }
            val pokemon = networkRepositoryImpl.getPokemon(id)
            val species = networkRepositoryImpl.getPokemonSpecies(result.url)

            val name = species.names.firstOrNull { it.language.name == "ko" }?.name ?: "Error"
            val genera = species.genera.firstOrNull { it.language.name == "ko" }?.genus ?: "Error"
            val image = pokemon.sprites.front_default // png
//            val image = pokemon.sprites.other.showdown.front_default //gif
            val types = pokemon.types.map { it.type.name }
            pokemons.add(PokemonEntity(name, id, image, genera, types))
        }

        return pokemons
    }
}