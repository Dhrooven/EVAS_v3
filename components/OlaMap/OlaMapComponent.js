import React from 'react';
import { requireNativeComponent, View } from 'react-native';
import { OLA_API_KEY } from '@env';

const OlaMapView = requireNativeComponent('OlaMapView');

const OlaMapComponent = ({ markers, ...props }) => {
  return (
    <View style={{ flex: 1 }}>
      <OlaMapView 
        style={{ flex: 1 }} 
        apiKey={OLA_API_KEY}
        markers={markers}
        {...props}
      />
    </View>
  );
};

export default OlaMapComponent;
