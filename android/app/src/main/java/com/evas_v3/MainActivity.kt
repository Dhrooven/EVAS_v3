package com.evas_v3

import android.os.Bundle
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import com.ola.mapsdk.interfaces.OlaMapCallback
import com.ola.mapsdk.view.OlaMap
import com.ola.mapsdk.view.OlaMapView

// import com.ola.mapsdk.OlaMapView
//import com.ola.mapsdk.MapStatusCallback
// import com.ola.mapsdk.OlaMapsConfig
// import okhttp3.OkHttpClient
// import com.ola.mapsdk.AccessTokenInterceptor

class MainActivity : ReactActivity() {

    private lateinit var olamapView: OlaMapView
    //private lateinit var mapStatusCallback: MapStatusCallback
    //lateinit var olaMapsConfig: OlaMapsConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        //
        // // Initialize OlaMapView
        // olamapView = findViewById(R.id.OlaMapView)
        //
        // olamapView.getMap(apiKey = "OLA_API_KEY",
        //   olaMapCallback = object : OlaMapCallback {
        //       override fun onMapReady(olaMap: OlaMap) {
        //           // Map is ready to use
        //       }
        //
        //       override fun onMapError(error: String) {
        //           // Handle map error
        //       }
          // }
        // )
}

    override fun getMainComponentName(): String = "EVAS_v3"

    override fun createReactActivityDelegate(): ReactActivityDelegate =
        DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)
}

