import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState } from 'react';
import CurrentReservations from './CurrentReservations'
import HistoryReservations from './HistoryReservations'

export default function AllReservations({asset, isSearch}){
    const [toggleState, setToggleState] = useState(1);

  const toggleTab = (index) => {
    setToggleState(index);
  };

  return (
    <div className="borderedBlock mb-3 mt-3">
      <div className="bloc-tabs">
        <button className={toggleState === 1 ? "tabs active-tabs" : "tabs"} onClick={() => toggleTab(1)} >
          Current Reservations
        </button>
        <button className={toggleState === 2 ? "tabs active-tabs" : "tabs"} onClick={() => toggleTab(2)} >
          History
        </button>
      </div>

      <div className="content-tabs">
        <div className={toggleState === 1 ? "content  active-content" : "content"} >
            <CurrentReservations/>
        </div>

        <div className={toggleState === 2 ? "content  active-content" : "content"} >
            <HistoryReservations/>
        </div>
      </div>
    </div>
  );
}


