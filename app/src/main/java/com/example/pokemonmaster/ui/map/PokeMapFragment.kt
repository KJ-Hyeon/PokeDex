package com.example.pokemonmaster.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonmaster.MainActivity
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.FragmentMapBinding
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource

class PokeMapFragment : Fragment(), OnMapReadyCallback {
    private val binding: FragmentMapBinding by lazy { FragmentMapBinding.inflate(layoutInflater) }
    private val mapFragment: MapFragment by lazy { childFragmentManager.findFragmentById(R.id.naver_map) as MapFragment }
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment.getMapAsync(this)
//        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.locationSource = (activity as MainActivity).locationSource
        binding.btnNaverLocation.map = naverMap
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}