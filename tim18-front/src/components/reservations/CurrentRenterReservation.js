import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect } from 'react';
import { getLogged } from '../../services/api/LoginApi'
import { ListedReservation } from './ListReservations'
import { getCurrentRenterReservations } from '../../services/api/ReservationApi';

export default function CurrentRenterReservations({renter}){
  const [reservations, setReservations] = useState();
  const [listedReservations, setListedReservations] = useState();

  useEffect(() => {
      async function fetchReservations(){
          await getCurrentRenterReservations(setReservations, renter.id);
      }
      if(renter !== undefined){
        fetchReservations();
      }
      
  }, [renter])
  
  useEffect(() => {
    if (reservations !== undefined){
        let listedReservations = reservations.map((reservation) => <ListedReservation reservation={reservation}/>)
        setListedReservations(listedReservations);
    }
  }, [reservations])
  
  return (
    <>
      {listedReservations}
    </>
  );
}

