package com.evas_v3

import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.ola.mapsdk.interfaces.OlaMapCallback
import com.ola.mapsdk.model.OlaLatLng
import com.ola.mapsdk.model.OlaMarkerOptions
import com.ola.mapsdk.view.Marker
import com.ola.mapsdk.view.OlaMap
import com.ola.mapsdk.view.OlaMapView

class OlaMapViewManager(private val reactContext: ReactApplicationContext) : SimpleViewManager<OlaMapView>() {
    private var olaMap: OlaMap? = null
    private var pendingMarkers: ReadableArray? = null
    private val markerMap: MutableMap<String, Marker> = mutableMapOf() // Use OlaMarker

    private val TAG = "OlaMapViewManager"

    override fun getName(): String {
        return "OlaMapView"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): OlaMapView {
        return OlaMapView(reactContext)
    }

    @ReactProp(name = "apiKey")
    fun setApiKey(view: OlaMapView, apiKey: String) {
        Log.d(TAG, "Setting API Key: $apiKey")
        view.getMap(apiKey = apiKey, olaMapCallback = object : OlaMapCallback {
            override fun onMapReady(map: OlaMap) {
                Log.d(TAG, "Map is ready")
                olaMap = map
                pendingMarkers?.let { addMarkers(it) }
                pendingMarkers = null
            }

            override fun onMapError(error: String) {
                Log.e(TAG, "Map error: $error")
                // Handle error appropriately
            }
        })
    }

    @ReactProp(name = "markers")
    fun setMarkers(view: OlaMapView, markers: ReadableArray) {
        Log.d(TAG, "setMarkers called with markers: ${markers.toArrayList()}")
        if (olaMap != null) {
            addOrUpdateMarkers(markers)
        } else {
            pendingMarkers = markers
        }
    }

    private fun addOrUpdateMarkers(markers: ReadableArray) {
        Log.d(TAG, "addOrUpdateMarkers called with markers: ${markers.toArrayList()}")
        // Create a set of incoming marker IDs
        val incomingMarkerIds = mutableSetOf<String>()
        for (i in 0 until markers.size()) {
            val marker: ReadableMap? = markers.getMap(i)
            if (marker != null) {
                val id = marker.getString("id") ?: continue
                incomingMarkerIds.add(id)
                val positionMap = marker.getMap("position")
                val lat = positionMap?.getDouble("latitude") ?: continue
                val lng = positionMap.getDouble("longitude") ?: continue
                val newPosition = OlaLatLng(lat, lng)

                if (markerMap.containsKey(id)) {
                    // Update existing marker position
                    Log.d(TAG, "Updating marker with id: $id to position: $lat, $lng")
                    markerMap[id]?.setPosition(newPosition)
                } else {
                    // Add new marker
                    Log.d(TAG, "Adding new marker with id: $id at position: $lat, $lng")
                    val isIconClickable = marker.getBoolean("isIconClickable")
                    val iconRotation = marker.getDouble("iconRotation").toFloat()
                    val isAnimationEnable = marker.getBoolean("isAnimationEnable")
                    val isInfoWindowDismissOnClick = marker.getBoolean("isInfoWindowDismissOnClick")

                    val markerOptions = OlaMarkerOptions.Builder()
                        .setMarkerId(id)
                        .setPosition(newPosition)
                        .setIsIconClickable(isIconClickable)
                        .setIconRotation(iconRotation)
                        .setIsAnimationEnable(isAnimationEnable)
                        .setIsInfoWindowDismissOnClick(isInfoWindowDismissOnClick)
                        .build()

                    val olaMarker = olaMap?.addMarker(markerOptions)
                    olaMarker?.let {
                        markerMap[id] = it
                        Log.d(TAG, "Marker added with id: $id")
                    }
                }
            }
        }

        // Remove markers that are no longer present
        val markersToRemove = markerMap.keys - incomingMarkerIds
        for (id in markersToRemove) {
            Log.d(TAG, "Removing marker with id: $id")
            markerMap[id]?.removeMarker()
            markerMap.remove(id)
        }
    }

    private fun addMarkers(markers: ReadableArray) {
        addOrUpdateMarkers(markers)
    }
}

