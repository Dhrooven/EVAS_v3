package com.evas_v3

import android.content.Context
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.ola.mapsdk.interfaces.OlaMapCallback
import com.ola.mapsdk.view.OlaMap
import com.ola.mapsdk.view.OlaMapView

class OlaMapViewManager(private val reactContext: ReactApplicationContext) : SimpleViewManager<OlaMapView>() {

    override fun getName(): String {
        return "OlaMapView"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): OlaMapView {
        val mapView = OlaMapView(reactContext)
        return mapView
    }

    @ReactProp(name = "apiKey")
    fun setApiKey(view: OlaMapView, apiKey: String) {
        // Set API key to initialize the map view
        view.getMap(apiKey = apiKey, olaMapCallback = object : OlaMapCallback {
            override fun onMapReady(olaMap: OlaMap) {
                // Handle when the map is ready
            }

            override fun onMapError(error: String) {
                // Handle error
            }
        })
    }
}
