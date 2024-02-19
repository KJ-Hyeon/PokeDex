package com.example.pokemonmaster.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonmaster.data.remote.dto.species.Pokemon
import com.example.pokemonmaster.data.remote.dto.speciespage.Result
import com.example.pokemonmaster.data.remote.dto.speciespage.SpeciesPageResponse
import com.example.pokemonmaster.data.repository.NetworkRepositoryImpl
import com.example.pokemonmaster.domain.entity.PokemonEntity
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

    private val _pokemon: MutableLiveData<MutableList<PokemonEntity>> = MutableLiveData()
    private val _speciesInfo: MutableLiveData<SpeciesPageResponse> = MutableLiveData()

    val pokemon: LiveData<MutableList<PokemonEntity>> get() = _pokemon
    val speciesInfo: LiveData<SpeciesPageResponse> get() = _speciesInfo


    fun getSpeciesPage(pageUrl: String) {
        viewModelScope.launch {
            _speciesInfo.value = networkRepositoryImpl.getPokemonSpeciesPage(
                pageUrl.ifEmpty { "pokemon-species" }
            )
        }
    }

    fun getPokemon(speciesUrls: List<Result>) {
        viewModelScope.launch {
            getPokemonUseCase(speciesUrls).let {
                val pokemonList = _pokemon.value ?: mutableListOf()
                pokemonList.addAll(it)
                _pokemon.value = pokemonList
            }
//            _pokemon.value = getPokemonUseCase(speciesUrls)
        }
    }

}