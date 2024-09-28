import React, { useState, useCallback } from 'react';
import { View, Button, StyleSheet } from 'react-native';
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

  const updateMarkers = useCallback(() => {
    console.log("Update Marker button pressed");
    setMarkers(prevMarkers => {
      const newMarkers = [
        ...prevMarkers,
        {
          id: `marker${prevMarkers.length + 1}`,
          position: { 
            latitude: 0 + (Math.random() - 0.5) * 0.01, 
            longitude: 0 + (Math.random() - 0.5) * 0.01 
          },
          isIconClickable: false,
          iconRotation: 45,
          isAnimationEnable: false,
          isInfoWindowDismissOnClick: false,
        },
      ];
      console.log("Markers updated:", newMarkers);
      return newMarkers;
    });
  }, []);

  return (
    <View style={styles.container}>
      <OlaMapComponent 
        style={styles.map}
        markers={markers} 
      />
      <Button title="Update Markers" onPress={updateMarkers} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    flex: 1,
  },
});

export default MapScreen;
