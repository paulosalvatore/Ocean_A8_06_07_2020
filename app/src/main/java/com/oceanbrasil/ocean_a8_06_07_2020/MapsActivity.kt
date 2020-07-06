package com.oceanbrasil.ocean_a8_06_07_2020

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun iniciarLocalizacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {
                return
            }
        } else {
            return
        }

        // Temos a permissão, podemos iniciar o LocationManager que pegará a localização do usuário

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationProvider = LocationManager.GPS_PROVIDER

        val ultimaLocalizacao = locationManager.getLastKnownLocation(locationProvider)

        ultimaLocalizacao?.let {
            val latLng = LatLng(it.latitude, it.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title("Minha posição"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))

            mMap.addCircle(
                CircleOptions()
                    .center(latLng)
                    .radius(50.0)
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE))
        }

        locationManager.requestLocationUpdates(
            locationProvider,
            1000L,
            1F,
            object : LocationListener {
                override fun onLocationChanged(location: Location?) {
                    location?.let {
                        val latLng = LatLng(it.latitude, it.longitude)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
                    }
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                }

                override fun onProviderEnabled(provider: String?) {
                }

                override fun onProviderDisabled(provider: String?) {
                }
            }
        )
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Adicionar um marker no Samsung Ocean - USP e mover a câmera até ele
        val samsungOcean = LatLng(-23.5567841, -46.7349922)
        mMap.addMarker(MarkerOptions().position(samsungOcean).title("Samsung Ocean - USP - São Paulo"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(samsungOcean, 17f))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(samsungOcean, 17f))

        iniciarLocalizacao()
    }
}