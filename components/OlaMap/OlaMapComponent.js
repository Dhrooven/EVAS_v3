import React from 'react';
import { requireNativeComponent } from 'react-native';
import { OLA_API_KEY } from '@env'; // Import the API key from the .env file
// import { OLA_API_KEY } from 'react-native-dotenv';

// This 'OlaMapView' should match the string name defined in your view manager (`OlaMapViewManager`)
const OlaMapView = requireNativeComponent('OlaMapView');

const OlaMapComponent = (props) => {
  return (
    <OlaMapView 
      style={{ flex: 1 }} 
      apiKey={OLA_API_KEY} // Pass the imported API key here
      {...props} // Pass other props (if any)
    />
  );
};

export default OlaMapComponent;

