package com.example.pokemonmaster.ui.map

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.pokemonmaster.MainActivity
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.FragmentMapBinding
import com.example.pokemonmaster.service.PokemonService
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource

class PokeMapFragment : Fragment(), OnMapReadyCallback {
    private val binding: FragmentMapBinding by lazy { FragmentMapBinding.inflate(layoutInflater) }
    private val mapFragment: MapFragment by lazy { childFragmentManager.findFragmentById(R.id.naver_map) as MapFragment }
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
        startLocationService()
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.locationSource = (activity as MainActivity).locationSource
        binding.btnNaverLocation.map = naverMap
    }

    private fun isLocationServiceRunning(): Boolean {
        // 현재 액티비티의 시스템 매니저를 가져온다.
        val activityManager = requireActivity().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        activityManager?.let {
            // 현재 실행 중인 애플리케이 프로세슴 목록을 가져온다.
            val runningAppProcess = activityManager.runningAppProcesses
            for (processInfo in runningAppProcess) {
                // 현재 순회 중인 프로세스의 이름이 일치하면 해당 서비스가 실행 중임을 나타낸다.
                if (processInfo.processName == "com.example.pokemonmaster:service.PokemonService") return true
            }
        }
        return false
    }

    private fun startLocationService() {
        if (!isLocationServiceRunning()) {
            val intent = Intent(requireActivity().applicationContext, PokemonService::class.java)
            intent.action = "startLocationService"
            requireActivity().startForegroundService(intent)
            Toast.makeText(requireContext(), "Location service started", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopLocationService() {
        if (isLocationServiceRunning()) {
            val intent = Intent(requireActivity().applicationContext, PokemonService::class.java)
            intent.action = "stopLocationService"
            requireActivity().startForegroundService(intent)
            Toast.makeText(requireContext(), "Location service stopped", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PokeMapFragment:","PokeMapFragment: onDestroy")
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}