package com.example.pokemonmaster.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pokemonmaster.R
import com.example.pokemonmaster.data.remote.dto.pokemon.Home
import com.example.pokemonmaster.databinding.FragmentHomeBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setDataObserve()
    }

    private fun initView() {
        initHomRecyclerView()
    }

    private fun setDataObserve() {
        homeViewModel.getSpeciesPage("")
        homeViewModel.speciesInfo.observe(viewLifecycleOwner) {
            homeViewModel.getPokemon(it.results)
        }
        homeViewModel.pokemon.observe(viewLifecycleOwner) {
            Log.d("HomeFragment","HomeFragment: $it")
            homeAdapter.submitList(it.toList())
        }
    }

    private fun initHomRecyclerView() {
        binding.revHome.adapter = homeAdapter
    }
}