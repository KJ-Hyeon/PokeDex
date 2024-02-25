package com.example.pokemonmaster.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.pokemonmaster.MainActivity
import com.example.pokemonmaster.R
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class PokemonService: Service() {
    private val LOCATION_SERVICE_ID = 1000
    private val channelId = "location_channel"
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            if (locationResult.lastLocation != null) {
                val latitude = locationResult.lastLocation?.latitude
                val longitude = locationResult.lastLocation?.longitude
                Log.d("PokemonService:","PokemonService: $latitude, $longitude")
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // 바운드 서비스가 아니기 때문에 null을 return
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            val action = intent.action
            action?.let { action ->
                when(action) {
                    "startLocationService" -> startLocationService()
                    "stopLocationService" -> stopLocationService()
                }
            }
        }
//        서비스가 강제로 종료되었을 때 시스템에 재시작을 요청
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelId,
            "Location Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Location Service Channel"
            setShowBadge(true)
        }
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    private fun createNotification(): Notification {
        // navigation을 통한 fragment의 전환이 이루어지기때문에 NavDeepLinkBuilder를 사용
        // TODO: mapFragment에 진입할 때마다 서비스 notification이 뜨는데 이걸 앱을 대기 상태에 올려두었을 때 뜨는 방식으로 아마도 생명주기 onPause()를 이용하면 되지않을까?

        val pendingIntent = NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.navigation)
            .setDestination(R.id.mapFragment)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.drawable.item_pokemon_ball) // 작은 아이콘 설정
            setContentTitle("Location Service") // 알림의 제목
            setDefaults(NotificationCompat.DEFAULT_ALL) // 사운드, 진동 및 라이트를 포함한 모든 기본 알림 속성
            setContentText("Run") // 본문 텍스트
            setContentIntent(pendingIntent) // 알림이 터치 되었을 경우, 실행 될 pendingIntent 설정
            setAutoCancel(false) // 알림을 터치해도 자동으로 취소가 되지않는다.
            priority = NotificationCompat.PRIORITY_MAX // 알림의 중요도를 설정 PRIORITY_MAX
        }

        return builder.build()
    }

    private fun startLocationService() {
        //이렇게 설정된 LocationRequest는 4초마다 위치 업데이트를 요청하며, 가능한 한 빠르게(2초 간격) 정확한 위치 정보를 얻으려고 합니다.
        val locationRequest = LocationRequest.create()
            .setInterval(4000) // 4초마다 한번씩 위치 업데이트를 요청한다..
            .setFastestInterval(2000) //  가장 빠른 위치 업데이트의 간격을 설정
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY) // 높은 정확도의 위치 업데이트를 요청한다.

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        LocationServices.getFusedLocationProviderClient(this)
            // 위치 업데이트를 요청하는 메서드
            .requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        createNotificationChannel()
        //         foreground 서비스를 시작 , 파라미터는 notification을 위한 요소들
        startForeground(LOCATION_SERVICE_ID, createNotification()) // setNotification의 builder가 들어가야함
    }

    private fun stopLocationService() {
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback)
        stopForeground(STOP_FOREGROUND_REMOVE) // foreground 서비스 종료 및 노티피케이션 제거
        stopSelf() // 서비스 종료
    }
}