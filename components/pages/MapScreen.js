import React, { useState, useEffect } from 'react';
import MapView, { Marker } from 'react-native-maps';
import { View, StyleSheet, Dimensions } from 'react-native';

const MapScreen = () => {
  const [region, setRegion] = useState({
    latitude: 28.7041, // Example: New Delhi
    longitude: 77.1025,
    latitudeDelta: 0.05,
    longitudeDelta: 0.05,
  });

  // Dummy data for emergency vehicles' locations
  const [emergencyVehicles, setEmergencyVehicles] = useState([
    { id: 1, type: 'ambulance', latitude: 28.706, longitude: 77.103 },
    { id: 2, type: 'police', latitude: 28.709, longitude: 77.101 },
  ]);

  return (
    <View style={styles.container}>
      <MapView
        style={styles.map}
        initialRegion={region}
        showsUserLocation={true}
      >
        {emergencyVehicles.map((vehicle) => (
          <Marker
            key={vehicle.id}
            coordinate={{
              latitude: vehicle.latitude,
              longitude: vehicle.longitude,
            }}
            title={vehicle.type.toUpperCase()}
            description={`Vehicle ID: ${vehicle.id}`}
            pinColor={vehicle.type === 'ambulance' ? 'red' : 'blue'}
          />
        ))}
      </MapView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    width: Dimensions.get('window').width,
    height: Dimensions.get('window').height / 2,
  },
});

export default MapScreen;
