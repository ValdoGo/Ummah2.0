package com.example.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Build
import android.util.Log
import com.example.data.repository.CityLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale
import java.util.TimeZone

object LocationHelper {
    private const val TAG = "LocationHelper"

    suspend fun autoDetectLocation(context: Context): CityLocation = withContext(Dispatchers.IO) {
        // 1. Try to get coordinates from Android LocationManager if permission is granted
        try {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
            if (locationManager != null) {
                val providers = locationManager.getProviders(true)
                var bestLocation: android.location.Location? = null
                for (provider in providers) {
                    try {
                        val loc = locationManager.getLastKnownLocation(provider)
                        if (loc != null && (bestLocation == null || loc.accuracy < bestLocation.accuracy)) {
                            bestLocation = loc
                        }
                    } catch (_: SecurityException) {}
                }

                if (bestLocation != null) {
                    val lat = bestLocation.latitude
                    val lng = bestLocation.longitude
                    val geocoder = Geocoder(context, Locale.getDefault())
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        // On Android 13+, geocoding is async, but we can do sync geocoding in IO coroutine
                        @Suppress("DEPRECATION")
                        val addresses = geocoder.getFromLocation(lat, lng, 1)
                        if (!addresses.isNullOrEmpty()) {
                            val addr = addresses[0]
                            val cityName = addr.locality ?: addr.subAdminArea ?: addr.adminArea ?: "Minha Cidade"
                            val countryName = addr.countryName ?: Locale.getDefault().displayCountry
                            return@withContext CityLocation(
                                name = cityName,
                                country = countryName,
                                latitude = lat,
                                longitude = lng,
                                timezone = TimeZone.getDefault().id
                            )
                        }
                    } else {
                        @Suppress("DEPRECATION")
                        val addresses = geocoder.getFromLocation(lat, lng, 1)
                        if (!addresses.isNullOrEmpty()) {
                            val addr = addresses[0]
                            val cityName = addr.locality ?: addr.subAdminArea ?: addr.adminArea ?: "Minha Cidade"
                            val countryName = addr.countryName ?: Locale.getDefault().displayCountry
                            return@withContext CityLocation(
                                name = cityName,
                                country = countryName,
                                latitude = lat,
                                longitude = lng,
                                timezone = TimeZone.getDefault().id
                            )
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "LocationManager detection error", e)
        }

        // 2. Fallback using device TimeZone and System Locale to determine country and primary city coordinates
        val tz = TimeZone.getDefault()
        val tzId = tz.id
        val locale = Locale.getDefault()
        val country = locale.displayCountry.ifBlank { "Local" }

        val detectedCity = mapTimezoneToCity(tzId, country)
        return@withContext detectedCity
    }

    private fun mapTimezoneToCity(tzId: String, countryFallback: String): CityLocation {
        return when {
            tzId.contains("Sao_Paulo") || tzId.contains("Recife") || tzId.contains("Bahia") ->
                CityLocation("São Paulo", "Brasil", -23.5505, -46.6333, tzId)
            tzId.contains("Rio_de_Janeiro") ->
                CityLocation("Rio de Janeiro", "Brasil", -22.9068, -43.1729, tzId)
            tzId.contains("Brasilia") || tzId.contains("Fortaleza") || tzId.contains("Manaus") ->
                CityLocation("Brasília", "Brasil", -15.7975, -47.8919, tzId)
            tzId.contains("Maputo") || tzId.contains("Mozambique") ->
                CityLocation("Maputo", "Moçambique", -25.9692, 32.5732, tzId)
            tzId.contains("Luanda") || tzId.contains("Angola") ->
                CityLocation("Luanda", "Angola", -8.8390, 13.2894, tzId)
            tzId.contains("Lisbon") || tzId.contains("Portugal") ->
                CityLocation("Lisboa", "Portugal", 38.7223, -9.1393, tzId)
            tzId.contains("London") || tzId.contains("Europe/London") ->
                CityLocation("Londres", "Reino Unido", 51.5074, -0.1278, tzId)
            tzId.contains("Paris") ->
                CityLocation("Paris", "França", 48.8566, 2.3522, tzId)
            tzId.contains("Madrid") ->
                CityLocation("Madrid", "Espanha", 40.4168, -3.7038, tzId)
            tzId.contains("Riyadh") || tzId.contains("Saudi") ->
                CityLocation("Riyadh", "Arábia Saudita", 24.7136, 46.6753, tzId)
            tzId.contains("Dubai") || tzId.contains("Gulf") ->
                CityLocation("Dubai", "Emirados Árabes", 25.2048, 55.2708, tzId)
            tzId.contains("Cairo") || tzId.contains("Egypt") ->
                CityLocation("Cairo", "Egito", 30.0444, 31.2357, tzId)
            tzId.contains("Istanbul") || tzId.contains("Turkey") ->
                CityLocation("Istambul", "Turquia", 41.0082, 28.9784, tzId)
            tzId.contains("Jakarta") || tzId.contains("Indonesia") ->
                CityLocation("Jacarta", "Indonésia", -6.2088, 106.8456, tzId)
            tzId.contains("Kuala_Lumpur") || tzId.contains("Malaysia") ->
                CityLocation("Kuala Lumpur", "Malásia", 3.1390, 101.6869, tzId)
            tzId.contains("Karachi") || tzId.contains("Pakistan") ->
                CityLocation("Karachi", "Paquistão", 24.8607, 67.0011, tzId)
            tzId.contains("New_York") || tzId.contains("Eastern") ->
                CityLocation("Nova York", "Estados Unidos", 40.7128, -74.0060, tzId)
            tzId.contains("Los_Angeles") || tzId.contains("Pacific") ->
                CityLocation("Los Angeles", "Estados Unidos", 34.0522, -118.2437, tzId)
            tzId.contains("Chicago") || tzId.contains("Central") ->
                CityLocation("Chicago", "Estados Unidos", 41.8781, -87.6298, tzId)
            else -> {
                val simpleName = tzId.substringAfterLast("/").replace("_", " ")
                CityLocation(
                    name = if (simpleName.isNotBlank() && simpleName != tzId) simpleName else "Localização Atual",
                    country = countryFallback,
                    latitude = -23.5505,
                    longitude = -46.6333,
                    timezone = tzId
                )
            }
        }
    }
}
