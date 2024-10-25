import React, { useState, useCallback } from 'react';
import { View, Button, StyleSheet, TextInput, Alert } from 'react-native';
import OlaMapComponent from '../OlaMap/OlaMapComponent.js';

const MapScreen = () => {
  const [markers, setMarkers] = useState([
    {
      id: 'marker1',
      position: { latitude: 18.52145653681468, longitude: 73.93178277572254 },
      isIconClickable: true,
      iconRotation: 0,
      isAnimationEnable: true,
      isInfoWindowDismissOnClick: true,
    },
  ]);

  // States for user input
  const [latitude, setLatitude] = useState('');
  const [longitude, setLongitude] = useState('');

  // Update markers when coordinates are input by the user
  const updateMarkers = useCallback(() => {
    if (!latitude || !longitude) {
      Alert.alert('Error', 'Please enter both latitude and longitude');
      return;
    }

    const lat = parseFloat(latitude);
    const lng = parseFloat(longitude);

    if (isNaN(lat) || isNaN(lng)) {
      Alert.alert('Error', 'Please enter valid numbers for latitude and longitude');
      return;
    }

    setMarkers(prevMarkers => {
      const updatedMarkers = prevMarkers.map(marker =>
        marker.id === 'marker1'
          ? {
              ...marker,
              position: { latitude: lat, longitude: lng },
            }
          : marker
      );
      console.log('Updated Markers:', updatedMarkers);
      return updatedMarkers;
    });
  }, [latitude, longitude]);

  return (
    <View style={styles.container}>
      <OlaMapComponent style={styles.map} markers={markers} />

      {/* Input fields for latitude and longitude */}
      <TextInput
        style={styles.input}
        placeholder="Enter Latitude"
        keyboardType="numeric"
        value={latitude}
        onChangeText={setLatitude}
      />
      <TextInput
        style={styles.input}
        placeholder="Enter Longitude"
        keyboardType="numeric"
        value={longitude}
        onChangeText={setLongitude}
      />

      {/* Button to update the marker */}
      <Button title="Update Marker" onPress={updateMarkers} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
  },
  map: {
    flex: 1,
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 10,
    paddingHorizontal: 10,
  },
});

export default MapScreen;

