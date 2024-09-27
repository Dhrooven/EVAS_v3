/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */
import 'react-native-gesture-handler'
import React from 'react'
import {NavigationContainer} from '@react-navigation/native'
import {createStackNavigator} from '@react-navigation/stack'

// Import Screens
import SplashScreen from './components/pages/SplashScreen'
import LoginScreen from './components/pages/LoginScreen'
import RegisterScreen from './components/pages/RegisterScreen'
import HomeScreen from './components/pages/HomeScreen'
import MapScreen from './components/pages/MapScreen'
import MapScreenTest from './components/pages/MapScreenTest'
const Stack = createStackNavigator()

const Auth = () => {
  // Stack Navigator for Login and Sign up Screen
  return (
    <Stack.Navigator initialRouteName='LoginScreen'>
      <Stack.Screen
        name='LoginScreen'
        component={LoginScreen}
        options={{headerShown: false}}
      />
      <Stack.Screen
        name='RegisterScreen'
        component={RegisterScreen}
        options={{
          title: 'Register', //Set Header Title
          headerStyle: {
            backgroundColor: '#307ecc', //Set Header color
          },
          headerTintColor: '#fff', //Set Header text color
          headerTitleStyle: {
            fontWeight: 'bold', //Set Header text style
          },
        }}
      />
    </Stack.Navigator>
  )
}

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName='MapScreenTest'>
        {/* SplashScreen which will come once for 2 Seconds */}
        <Stack.Screen
          name='SplashScreen'
          component={SplashScreen}
          // Hiding header for Splash Screen
          options={{headerShown: false}}
        />
        {/* Auth Navigator which include Login Signup */}
        <Stack.Screen
          name='Auth'
          component={Auth}
          options={{headerShown: false}}
        />

        <Stack.Screen
          name='HomeScreen'
          component={HomeScreen}
          options={{
            title: 'Home', //Set Header Title
            headerStyle: {
              backgroundColor: '#307ecc', //Set Header color
            },
            headerTintColor: '#fff', //Set Header text color
            headerTitleStyle: {
              fontWeight: 'bold', //Set Header text style
            },
          }}
        />

        <Stack.Screen
          name='MapScreen'
          component={MapScreen}
          // Hiding header for Splash Screen
          options={{headerShown: false}}
        />

        <Stack.Screen
          name='MapScreenTest'
          component={MapScreenTest}
          // Hiding header for Splash Screen
          options={{headerShown: true}}
        />
      </Stack.Navigator>
    </NavigationContainer>
  )
}

export default App
