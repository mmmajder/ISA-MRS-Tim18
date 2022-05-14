import React from 'react';
import '../../assets/styles/asset.css';
import {Map, Marker, GoogleApiWrapper} from 'google-maps-react';
import '../../assets/styles/style.css'; 

// reference https://stackoverflow.com/questions/61277920/how-to-display-google-map-in-reactjs-inside-of-page-height-and-width

export function MapContainer({google, locations = []}){

    return      (<Map 
        google={google}
        
        style={ {
            width: '100%',
            height: '300px'
            }}
        center={{
            lat: 45.81621,
            lng: 19.6188895
            }}
        initialCenter={
            {
              lat: 45.81621,
              lng: 19.6188895
            }}  
        className="mapContainerWrapper"
        zoom={16}
    >
        <Marker position={
            {
              lat: 45.81621,
              lng: 19.6188895
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
