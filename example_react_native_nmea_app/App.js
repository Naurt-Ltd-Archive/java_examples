import React, {useEffect } from "react";
import {
  NativeModules, StyleSheet, Text, View,
  DeviceEventEmitter, PermissionsAndroid,
} from "react-native";


const { Nmea } = NativeModules;

const App = () => {

  //onload functions
  useEffect(() => {
    requestLocationPermission().then((r) => {
      nmeaStreamStart()
      getNmea();
    });
  }, []);

  //starts nmea listener
  function nmeaStreamStart() {
    Nmea.start();
  }


  function getNmea() {
    console.log("HERE")
    DeviceEventEmitter.addListener("onNmeaReceive", (event) => {
      console.log(event)
    });
  }

  async function requestLocationPermission() {
    try {
      const granted = await PermissionsAndroid.request(
          PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
          {
            title: "Naurt Demo App Location Permission",
            message: "Naurt Demo App needs access to your location ",
            buttonNegative: "Cancel",
            buttonPositive: "OK",
          }
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log("You can use location services");
      } else {
        console.log("location permission denied");
      }
    } catch (err) {
      console.warn(err);
    }
  }




  return (
      <View >
        <Text>HI</Text>
      </View>


  );
};

export default App;
