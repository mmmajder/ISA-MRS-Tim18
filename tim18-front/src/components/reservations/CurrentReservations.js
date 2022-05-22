import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect } from 'react';
import AssetTypeOption from '../asset/AssetTypeOption';
import { getLogged } from '../../services/api/LoginApi'
import { ListedReservation } from './ListReservations'
import { cancelReservation, getCurrentReservationsByType } from '../../services/api/ReservationApi';

export default function CurrentReservations(){
  const [assetType, setAssetType] = useState("ALL");
  const [reservations, setReservations] = useState();
  const [client, setClient] = useState();
  const [listedReservations, setListedReservations] = useState();

  useEffect(() => {
      async function fetchUser(){
          await getLogged(setClient);
      }
      fetchUser();
  }, [])

  useEffect(() => {
      async function fetchReservations(){
          await getCurrentReservationsByType(setReservations, client.id, assetType);
      }
      if(client !== undefined){
        fetchReservations();
      }
      
  }, [client, assetType])

  const handleChange = (reservation) => {
    cancelReservation(reservation.id)
    setReservations(reservations.filter(item => item.id !== reservation.id));
  }
  
  useEffect(() => {
    if (reservations !== undefined){
        let listedReservations = reservations.map((reservation) => <ListedReservation reservation={reservation} handleChange={handleChange}/>)
        setListedReservations(listedReservations);
    }
  }, [reservations])
  

  
  return (
    <>
      <AssetTypeOption setAssetType={setAssetType}/>
      {listedReservations}
    </>
  );
}

