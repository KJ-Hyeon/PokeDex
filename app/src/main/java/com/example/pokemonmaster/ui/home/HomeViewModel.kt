package com.example.pokemonmaster.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonmaster.data.remote.dto.species.Pokemon
import com.example.pokemonmaster.data.remote.dto.speciespage.Result
import com.example.pokemonmaster.data.remote.dto.speciespage.SpeciesPageResponse
import com.example.pokemonmaster.data.repository.NetworkRepositoryImpl
import com.example.pokemonmaster.data.repository.PokemonWithSpecies
import com.example.pokemonmaster.domain.entity.PokemonEntity
import com.example.pokemonmaster.domain.entity.SpeciesPageEntity
import com.example.pokemonmaster.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepositoryImpl: NetworkRepositoryImpl,
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    // entity를 받아서
    private val _pokemon: MutableLiveData<MutableList<HomePokeItem>> = MutableLiveData()
    private val _speciesInfo: MutableLiveData<PageInfo> = MutableLiveData()

    val pokemon: LiveData<MutableList<HomePokeItem>> get() = _pokemon
    val speciesInfo: LiveData<PageInfo> get() = _speciesInfo


    fun getSpeciesPage(pageUrl: String) {
        viewModelScope.launch {
            val pageInfo = networkRepositoryImpl.getPokemonSpeciesPage(
                pageUrl.ifEmpty { "pokemon-species" }
            ).convertPageInfo()
            _speciesInfo.value = pageInfo
        }
    }

    fun getPokemon(speciesUrls: List<String>) {
        viewModelScope.launch {
            val pokemonList = _pokemon.value ?: mutableListOf()
            getPokemonUseCase(speciesUrls).forEach {
                pokemonList.add(it.convertPokemon())
            }
            pokemonList.sortBy { it.number?.toInt() }
            _pokemon.value = pokemonList
//            _isLoading.value = false
        }
    }
}

private fun PokemonWithSpecies.convertPokemon() = HomePokeItem(
    // usercase에서 return 받은 entity로 pokemon dataclass 구성
    name = this.sepecies.name,
    number = this.sepecies.id.toString(),
    image = this.pokemon.sprites,
    genera = this.sepecies.genera,
    type = this.pokemon.types
)

private fun SpeciesPageEntity.convertPageInfo() = PageInfo(
    next = this.next,
    results = this.results
)
