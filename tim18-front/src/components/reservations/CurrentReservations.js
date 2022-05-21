import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect } from 'react';
import AssetTypeOption from '../asset/AssetTypeOption';
import { getLogged } from '../../services/api/LoginApi'
import { ListedReservation } from './ListReservations'
import { getCurrentReservationsByType } from '../../services/api/ReservationApi';

export default function CurrentReservations(){
  const [assetType, setAssetType] = useState("ALL");
  const [reservations, setReservations] = useState();
  const [client, setClient] = useState();
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

  let listedReservations;
    if (reservations !== undefined){
      listedReservations = reservations.map((reservation) =>  <ListedReservation reservation={reservation}/>)
  }
  return (
    <>
      <AssetTypeOption setAssetType={setAssetType}/>
      {listedReservations}
    </>
  );
}

