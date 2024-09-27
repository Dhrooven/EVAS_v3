package com.evas_v3

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.ola.mapsdk.interfaces.OlaMapCallback
import com.ola.mapsdk.view.OlaMap
import com.ola.mapsdk.view.OlaMapView

class OlaMapModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "OlaMapModule"

    @ReactMethod
    fun initializeMap(promise: Promise) {
        val activity = currentActivity ?: run {
            promise.reject("ERROR", "Activity is null")
            return
        }

        activity.runOnUiThread {
            val mapView = OlaMapView(activity)
            mapView.getMap(apiKey = "OLA_API_KEY", olaMapCallback = object : OlaMapCallback {
                override fun onMapReady(olaMap: OlaMap) {
                    promise.resolve("Map initialized successfully")
                }

                override fun onMapError(error: String) {
                    promise.reject("MAP_ERROR", error)
                }
            })
        }
    }
}
