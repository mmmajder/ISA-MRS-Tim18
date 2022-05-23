import React from 'react';
import '../../assets/styles/asset.css';
import {Map, Marker, GoogleApiWrapper} from 'google-maps-react';
import '../../assets/styles/style.css'; 
import axios from 'axios'
import {useState} from 'react';

// google maps api https://stackoverflow.com/questions/61277920/how-to-display-google-map-in-reactjs-inside-of-page-height-and-width
// positionstack geocoding 
//      data returns like: http://api.positionstack.com/v1/forward?access_key=c34ca23bddd0452df4c8b1bc32eb43a7&query=Backa+Topola,+Petefi+Sandora+14
//      documentation https://positionstack.com/documentation

export function MapContainer({google, address}){

    const [latitude, setLatitude] = useState(0);
    const [longitude, setLongitude] = useState(0);

    const api = axios.create({
        baseURL: "http://api.positionstack.com/v1",
        headers:  {"Content-Type": "application/json"}
    })

    async function getCoordinates() {
        api.get(`/forward`,  {
            params: {
                "access_key" : "c34ca23bddd0452df4c8b1bc32eb43a7",
                "query":  address
            }
          }).then((response) => {
                let place = response.data.data[0];
                let lat = JSON.stringify(place.latitude);
                let lon = JSON.stringify(place.longitude);

            console.log(lat, lon);
            setLatitude(lat);
            setLongitude(lon);
          }, (error) => {
            console.log(error);
          }); 
    }

    getCoordinates();

    return      (<Map 
        google={google}
        
        style={ {
            width: '100%',
            height: '300px'
            }}
        center={{
            lat: latitude,
            lng: longitude
            }}
        initialCenter={
            {
              lat: latitude,
              lng: longitude
            }}  
        className="mapContainerWrapper"
        zoom={16}
    >
        <Marker position={
            {
              lat: latitude,
              lng: longitude
            }
        }  />

        <style jsx>{`
            .mapContainerWrapper{
                position:relative !important;   
            }

            .mapContainerWrapper div:first-child{
                position:relative !important;
            }
        `}</style>

    </Map>)              
};

export default GoogleApiWrapper({
    apiKey: "AIzaSyDlfiUh3y4siKOGBDGDD1404F-mTOiga3E"
})(MapContainer);