import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect} from 'react';
import CurrentReservations from './CurrentReservations'
import HistoryReservations from './HistoryReservations'
import CurrentRenterReservations from './CurrentRenterReservation';
import { getLogged } from '../../services/api/LoginApi'
import HistoryRenterReservations from './HistoryRenterReservations';

export default function AllReservations(){
    const [toggleState, setToggleState] = useState(1);
    const [user, setUser] = useState();
    const [currentReservations, setCurrentReservations] = useState();
    const [historyReservations, setHistoryReservations] = useState();

  const toggleTab = (index) => {
    setToggleState(index);
  };

  useEffect(() => {
    async function fetchUser(){
        await getLogged(setUser);
    }
    fetchUser();
  }, [])

  useEffect(() => {
    if (user != undefined){
      let currentReservationss;
      let historyReservationss;
      if (user.userType == "Client"){
        currentReservationss = <CurrentReservations client={user}/> 
        historyReservationss = <HistoryReservations client={user}/> 
      } else {
        currentReservationss = <CurrentRenterReservations renter={user}/>
        historyReservationss = <HistoryRenterReservations renter={user}/>
      }
      setCurrentReservations(currentReservationss);
      setHistoryReservations(historyReservationss);
    }
  }, [user])
  
  return (
    <div className="borderedBlock mb-3 mt-3">
      <div className="bloc-tabs">
        <button style={{fontSize: "20px"}} className={toggleState === 1 ? "tabs active-tabs" : "tabs"} onClick={() => toggleTab(1)} >
          Reservations
        </button>
        <button style={{fontSize: "20px"}} className={toggleState === 2 ? "tabs active-tabs" : "tabs"} onClick={() => toggleTab(2)} >
          History
        </button>
      </div>

      <div className="content-tabs">
        <div className={toggleState === 1 ? "content  active-content" : "content"} >
            {currentReservations}
        </div>
        <div className={toggleState === 2 ? "content  active-content" : "content"} >
          {historyReservations}
        </div>
      </div>
    </div>
  );
}


