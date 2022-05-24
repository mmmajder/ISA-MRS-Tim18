import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect } from 'react';
import AssetTypeOption from '../asset/AssetTypeOption';
import { getLogged } from '../../services/api/LoginApi'
import { ListedReservation } from './ListReservations'
import { cancelReservation, getCurrentReservationsByType } from '../../services/api/ReservationApi';
import { ConfirmModal } from '../modal/ConfirmModal';

export default function CurrentReservations(){
  const [assetType, setAssetType] = useState("ALL");
  const [reservations, setReservations] = useState();
  const [client, setClient] = useState();
  const [listedReservations, setListedReservations] = useState();
  const [show, setShow] = useState(false);

  const [canceledReservation, setCanceledReservation] = useState();
  const message="Are you sure you want to cancel this reservation?\nYou won't be able to reserve these dates again.";

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
    setShow(true);
    setCanceledReservation(reservation);
  }
  
  useEffect(() => {
    if (reservations !== undefined){
        let listedReservations = reservations.map((reservation) => <ListedReservation reservation={reservation} handleChange={handleChange}/>)
        setListedReservations(listedReservations);
    }
  }, [reservations])
  
  const handleConfirm = (result) => {
    setShow(false);
    if(result !== 'Cancel'){
      cancelReservation(canceledReservation.id)
      setReservations(reservations.filter(item => item.id !== canceledReservation.id));
    }
  }

  
  return (
    <>
      <AssetTypeOption setAssetType={setAssetType}/>
      {listedReservations}
      {<ConfirmModal message={message} show={show} handleClose={handleConfirm}/>}
    </>
  );
}

