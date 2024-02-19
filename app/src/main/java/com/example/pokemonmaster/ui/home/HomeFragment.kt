package com.example.pokemonmaster.ui.home

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonmaster.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter() }
    private var recyclerViewState: Parcelable? = null
    private var isLoading: Boolean = false
    private lateinit var nextPageUrl: String

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
            nextPageUrl = it.next
            isLoading = false
        }
        homeViewModel.pokemon.observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
        }
    }

    private fun initHomRecyclerView() {
        with(binding.revHome) {
            adapter = homeAdapter
            post {
                layoutManager?.onRestoreInstanceState(recyclerViewState)
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    checkLastItem(recyclerView)
                }
            })
        }
    }

    private fun checkLastItem(rev: RecyclerView) {
        val layoutManager = rev.layoutManager as LinearLayoutManager
        val lastItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if (!isLoading && lastItemPosition == totalItemCount - 3) {
            isLoading = true
            Log.d("HomeFragment","HomeFragment: load next page")
            homeViewModel.getSpeciesPage(nextPageUrl)
        }
    }

}