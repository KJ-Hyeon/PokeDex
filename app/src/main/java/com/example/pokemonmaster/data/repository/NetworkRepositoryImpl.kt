package com.example.pokemonmaster.data.repository

import com.example.pokemonmaster.data.remote.dto.pokemon.PokemonResponse
import com.example.pokemonmaster.data.remote.dto.species.SpeciesResponse
import com.example.pokemonmaster.data.remote.dto.speciespage.SpeciesPageResponse
import com.example.pokemonmaster.data.remote.retrofit.PokeService
import com.example.pokemonmaster.domain.entity.PokemonEntity
import com.example.pokemonmaster.domain.entity.SpeciesEntity
import com.example.pokemonmaster.domain.entity.SpeciesPageEntity
import com.example.pokemonmaster.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokeService
): NetworkRepository {
    // entity를 만들고 return
    override suspend fun getPokemonSpeciesPage(url: String) = withContext(Dispatchers.IO){
        val speciesPage = remoteDataSource.getPokemonSpeciesPage(url)
        convertSpeciesPageEntity(speciesPage)
    }

    override suspend fun getPokemonSpecies(url: String) = withContext(Dispatchers.IO){
        val species = remoteDataSource.getPokemonSpecies(url)
        convertSpeciesEntity(species)
    }

    override suspend fun getPokemon(id: String) = withContext(Dispatchers.IO){
        val pokemon = remoteDataSource.getPokemon(id)
        convertPokemonEntity(pokemon)
    }

    private fun convertSpeciesPageEntity(item: SpeciesPageResponse): SpeciesPageEntity {
        return SpeciesPageEntity(
            count = item.count,
            next = item.next,
            previous = item.previous,
            results = item.results.map { it.url }
        )
    }

    private fun convertPokemonEntity(item: PokemonResponse): PokemonEntity {
        return PokemonEntity(
            abilities = item.abilities.map { it.ability.name },
            base_experience = item.base_experience,
            cries = item.cries.legacy,
            forms = item.forms.get(0).url,
            height = item.height,
            weight = item.weight,
            id = item.id,
            location_area_encounters = item.location_area_encounters,
            name = item.name,
            order = item.order,
            sprites = item.sprites.versions.generation_v.black_white.animated.front_default,
            types = item.types.map { it.type.name }
        )
    }

    private fun convertSpeciesEntity(item: SpeciesResponse): SpeciesEntity {
        return SpeciesEntity(
            base_happiness = item.base_happiness,
            capture_rate = item.capture_rate,
            color = item.color.name,
            egg_groups = item.egg_groups.map { it.name },
            evolution_chain = item.evolution_chain.url,
            flavor_text_entries = item.flavor_text_entries.firstOrNull {
                it.language.name == "ko" && it.version.name == "y"
            }?.flavor_text ?: "Error",
            gender_rate = item.gender_rate,
            genera = item.genera.firstOrNull { it.language.name == "ko" }?.genus ?: "Error",
            hatch_counter = item.hatch_counter,
            id = item.id,
            is_baby = item.is_baby,
            is_legendary = item.is_legendary,
            is_mythical = item.is_mythical,
            name = item.names.firstOrNull {it.language.name == "ko"}?.name ?: "Error",
            pokeUrl = item.varieties[0].pokemon.url
        )
    }
}
data class PokemonWithSpecies(
    val pokemon: PokemonEntity, // Entity를 받고
    val sepecies: SpeciesEntity // Entity를 받고
)