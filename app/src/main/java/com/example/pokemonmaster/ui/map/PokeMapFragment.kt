package com.example.pokemonmaster.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.FragmentMapBinding
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class PokeMapFragment : Fragment(), OnMapReadyCallback {
    private val binding: FragmentMapBinding by lazy { FragmentMapBinding.inflate(layoutInflater) }
    private val mapFragment: MapFragment by lazy { childFragmentManager.findFragmentById(R.id.naver_map) as MapFragment }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        TODO("Not yet implemented")
    }
}